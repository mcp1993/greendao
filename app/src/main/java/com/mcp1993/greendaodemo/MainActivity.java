package com.mcp1993.greendaodemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mcp1993.greendaodemo.bean.Challenge_horBean;
import com.mcp1993.greendaodemo.bean.Challenge_horData;
import com.mcp1993.greendaodemo.dao.Challenge_horDataDao;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  implements HorRecyclerAdapter.OnItemClickListener{

    private RecyclerView recy_clerview;
    private List<Challenge_horData> datas = new ArrayList<>();
    private HorRecyclerAdapter mAdapter;

    private static final int NOHTTP_WHAT_TEST = 0x001;
    private RequestQueue mQueue;
    private Object sign = new Object();
    private Challenge_horBean bean;
    private long count;//用来记录从数据库取出指定项的数量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy_clerview = (RecyclerView) findViewById(R.id.recy_clerview);
        initRecy();

        //读缓存,判断数据库是否为空，网络状态
        if (null != APP.getDaoSession().getChallenge_horDataDao().loadAll()
                && 0 <APP.getDaoSession().getChallenge_horDataDao().loadAll().size()
                    && !NetworkUtil.isAvailable(MainActivity.this)){
            datas.clear();//清空集合，为了保险起见
            datas.addAll(APP.getDaoSession().getChallenge_horDataDao().loadAll());

            mAdapter = new HorRecyclerAdapter(datas, MainActivity.this);
            recy_clerview.setAdapter(mAdapter);
            recy_clerview.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                    super.onTouchEvent(rv, e);
                }
            });
            mAdapter.setOnItemClickListener(this);
        }else {
            loadData();
        }
    }

    //recyclerview item 点击事件
    @Override
    public void onClick(RecyclerView.ViewHolder VH, int position) {

    }

    private void loadData(){
        mQueue = NoHttp.newRequestQueue();
        // 创建请求对象。
        Request<String> request = NoHttp.createStringRequest("http://v.juhe.cn/toutiao/index",
                RequestMethod.GET);

        // 添加请求参数。
        request.add("type", "top")
                .add("key", "e3e6514ccfa98992c198c33683e35c5d")
                // 单个请求的超时时间，不指定就会使用全局配置。
                .setConnectTimeout(10 * 1000) // 设置连接超时。
                .setReadTimeout(20 * 1000) // 设置读取超时时间，也就是服务器的响应超时。
                // 设置一个tag, 在请求完(失败/成功)时原封不动返回; 多数情况下不需要。
                .setTag(new Object())
                // 设置取消标志。
                .setCancelSign(sign);

		/*
         * what: 当多个请求同时使用同一个OnResponseListener时用来区分请求, 类似handler的what一样。
		 * request: 请求对象。
		 * onResponseListener 回调对象，接受请求结果。
		 */
        mQueue.add(NOHTTP_WHAT_TEST, request, onResponseListener);
    }

    //初始化recyclerview
    private void initRecy(){
        recy_clerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL ,false));
        //确保尺寸是一个常数，避免计算每个item的size
        recy_clerview.setHasFixedSize(true);
        //设置显示动画
        recy_clerview.setItemAnimator(new DefaultItemAnimator());
        recy_clerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) !=-1){
                    outRect.top  = 20;
                    outRect.right = 20;
                }
            }
        });
    }


    /**
     * 回调对象，接受请求结果.
     */
    private OnResponseListener<String> onResponseListener = new OnResponseListener<String>() {

        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response<String> response) {
            if (what == NOHTTP_WHAT_TEST) {// 根据what判断是哪个请求的返回，这样就可以用一个OnResponseListener来接受多个请求的结果。

//           String result = response.get();// 响应结果。
                bean = JSON.parseObject(response.get(),Challenge_horBean.class);
                for (int i = 0;i<bean.getResult().getData().size();i++){
                    datas.add(bean.getResult().getData().get(i));
                }
                mAdapter = new HorRecyclerAdapter(datas, MainActivity.this);
                recy_clerview.setAdapter(mAdapter);
                recy_clerview.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                        super.onTouchEvent(rv, e);
                    }
                });

                //将数据插入数据库
                Observable.from(datas).subscribeOn(Schedulers.io()).subscribe(challenge_horData -> {
                         //避免插入重复数据的逻辑
                    count = APP.getDaoSession().getChallenge_horDataDao().queryBuilder().where(Challenge_horDataDao.Properties.Uniquekey.eq(challenge_horData.getUniquekey())).count();
                    if (count == 0){
                        APP.getDaoSession().getChallenge_horDataDao().insertOrReplaceInTx(challenge_horData);
                    }else {
                        //刷新操作，清除bean，插入数据
                        APP.getDaoSession().getChallenge_horDataDao().deleteAll();
                        APP.getDaoSession().getChallenge_horDataDao().insertOrReplaceInTx(challenge_horData);
                    }

                });
            }
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            Toast.makeText(MainActivity.this,"网络出错",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish(int what) {

        }
    };
}
