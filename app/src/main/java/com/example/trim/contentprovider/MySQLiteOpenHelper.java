package com.example.trim.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/4/23.数据库创建类
 * MySQLiteOpenHelper--》
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "MySQLiteOpenHelper: ";
    private static String CREATE_TABLE = "create table Persons (" +
            "_id integer primary key autoincrement," +
            "name text," +
            "age integer," +
            "sex text)";

    private Context mContext;
    /**
     * 帮组我们去创建或者打开相应的数据库
     * 1、上下文对象
     * 2、数据库的文件名  一般用.db
     * 3、CursorFactory  一般都是用null来表示使用系统默认的
     * 4、版本号
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        MyLog.i(TAG+"MySQLiteOpenHelper Construct");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MyLog.i(TAG+"onCreate");
        MyLog.i(TAG+"getDatabaseName: "+getDatabaseName());

        db.execSQL(CREATE_TABLE);//创建一张表格
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MyLog.i(TAG+"onUpgrade");
        db.execSQL("drop table if exists Persons");
    }

    @Override
    public synchronized void close() {
        super.close();
        MyLog.i(TAG+"close");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        MyLog.i(TAG+"onOpen");
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }
}
