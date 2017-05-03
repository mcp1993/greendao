package com.mcp1993.greendaodemo;

import android.app.Application;

import com.mcp1993.greendaodemo.dao.DaoMaster;
import com.mcp1993.greendaodemo.dao.DaoSession;
import com.yanzhenjie.nohttp.NoHttp;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public class APP extends Application{
    public static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();//初始化greendao数据库
        NoHttp.initialize(this);
    }

    private void initGreenDao() {
        //DevOpenHelper每次数据库升级会清空数据，一般用于开发
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"geendao_db",null);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }
}
