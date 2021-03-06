package com.mcp1993.greendaodemo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mcp1993.greendaodemo.bean.Challenge_horData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHALLENGE_HOR_DATA".
*/
public class Challenge_horDataDao extends AbstractDao<Challenge_horData, Void> {

    public static final String TABLENAME = "CHALLENGE_HOR_DATA";

    /**
     * Properties of entity Challenge_horData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uniquekey = new Property(0, String.class, "uniquekey", false, "UNIQUEKEY");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Date = new Property(2, String.class, "date", false, "DATE");
        public final static Property Category = new Property(3, String.class, "category", false, "CATEGORY");
        public final static Property Author_name = new Property(4, String.class, "author_name", false, "AUTHOR_NAME");
        public final static Property Url = new Property(5, String.class, "url", false, "URL");
        public final static Property Thumbnail_pic_s = new Property(6, String.class, "thumbnail_pic_s", false, "THUMBNAIL_PIC_S");
        public final static Property Thumbnail_pic_s02 = new Property(7, String.class, "thumbnail_pic_s02", false, "THUMBNAIL_PIC_S02");
        public final static Property Thumbnail_pic_s03 = new Property(8, String.class, "thumbnail_pic_s03", false, "THUMBNAIL_PIC_S03");
    }


    public Challenge_horDataDao(DaoConfig config) {
        super(config);
    }
    
    public Challenge_horDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHALLENGE_HOR_DATA\" (" + //
                "\"UNIQUEKEY\" TEXT," + // 0: uniquekey
                "\"TITLE\" TEXT," + // 1: title
                "\"DATE\" TEXT," + // 2: date
                "\"CATEGORY\" TEXT," + // 3: category
                "\"AUTHOR_NAME\" TEXT," + // 4: author_name
                "\"URL\" TEXT," + // 5: url
                "\"THUMBNAIL_PIC_S\" TEXT," + // 6: thumbnail_pic_s
                "\"THUMBNAIL_PIC_S02\" TEXT," + // 7: thumbnail_pic_s02
                "\"THUMBNAIL_PIC_S03\" TEXT);"); // 8: thumbnail_pic_s03
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHALLENGE_HOR_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Challenge_horData entity) {
        stmt.clearBindings();
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(1, uniquekey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String author_name = entity.getAuthor_name();
        if (author_name != null) {
            stmt.bindString(5, author_name);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String thumbnail_pic_s = entity.getThumbnail_pic_s();
        if (thumbnail_pic_s != null) {
            stmt.bindString(7, thumbnail_pic_s);
        }
 
        String thumbnail_pic_s02 = entity.getThumbnail_pic_s02();
        if (thumbnail_pic_s02 != null) {
            stmt.bindString(8, thumbnail_pic_s02);
        }
 
        String thumbnail_pic_s03 = entity.getThumbnail_pic_s03();
        if (thumbnail_pic_s03 != null) {
            stmt.bindString(9, thumbnail_pic_s03);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Challenge_horData entity) {
        stmt.clearBindings();
 
        String uniquekey = entity.getUniquekey();
        if (uniquekey != null) {
            stmt.bindString(1, uniquekey);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String author_name = entity.getAuthor_name();
        if (author_name != null) {
            stmt.bindString(5, author_name);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(6, url);
        }
 
        String thumbnail_pic_s = entity.getThumbnail_pic_s();
        if (thumbnail_pic_s != null) {
            stmt.bindString(7, thumbnail_pic_s);
        }
 
        String thumbnail_pic_s02 = entity.getThumbnail_pic_s02();
        if (thumbnail_pic_s02 != null) {
            stmt.bindString(8, thumbnail_pic_s02);
        }
 
        String thumbnail_pic_s03 = entity.getThumbnail_pic_s03();
        if (thumbnail_pic_s03 != null) {
            stmt.bindString(9, thumbnail_pic_s03);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Challenge_horData readEntity(Cursor cursor, int offset) {
        Challenge_horData entity = new Challenge_horData( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // uniquekey
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // date
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // category
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // author_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // url
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // thumbnail_pic_s
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // thumbnail_pic_s02
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // thumbnail_pic_s03
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Challenge_horData entity, int offset) {
        entity.setUniquekey(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCategory(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAuthor_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setThumbnail_pic_s(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setThumbnail_pic_s02(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setThumbnail_pic_s03(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Challenge_horData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Challenge_horData entity) {
        return null;
    }

    @Override
    public boolean hasKey(Challenge_horData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
