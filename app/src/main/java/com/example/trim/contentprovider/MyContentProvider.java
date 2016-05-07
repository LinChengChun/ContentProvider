package com.example.trim.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/4/23.
 */
public class MyContentProvider extends ContentProvider {

    private static final String TAG = "MyContentProvider";
    private static final String AUTHORITY = "com.example.trim.contentprovider";
    private static UriMatcher matcher;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String DataBaseName = "0423.db";
    private static String TableName = "Persons";

    private static final int PERSON = 0;
    private static final int PERSONS = 1;

    static{
        matcher = new UriMatcher(UriMatcher.NO_MATCH);  //创建一个uri匹配器
        matcher.addURI(AUTHORITY, "person", PERSONS);  // path一般跟的是表名  content://com.qianfeng.provider/person
        matcher.addURI(AUTHORITY, TableName, PERSON);

    }

    @Override
    public boolean onCreate() {
        this.mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(), DataBaseName, null, 1);
        MyLog.i(TAG+" onCreate()");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        if(matcher.match(uri) == PERSON){
            return db.query(TableName, projection, selection, selectionArgs, null, null, sortOrder);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 插入数据
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        MyLog.i(uri.toString());

        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        if(matcher.match(uri) == PERSON){
            db.insert(TableName, null, values);
            db.close();
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        if(matcher.match(uri) == PERSON){
            int ret = db.delete(TableName, selection, selectionArgs);
            db.close();
            return ret;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        if(matcher.match(uri) == PERSON){
            int i = db.update(TableName, values, selection, selectionArgs);
            db.close();
            return i;
        }
        return 0;
    }
}
