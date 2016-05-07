package com.example.trim.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/4/23.
 * MySQLiteAccess --》数据库操作类
 */
public class MySQLiteAccess {

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String DataBaseName = "0423.db";

    public MySQLiteAccess(Context context, int version) {

        this.mySQLiteOpenHelper = new MySQLiteOpenHelper(context, DataBaseName, null, 1);

    }

    public void insertPerson(Person person){
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();

        String SQLcammand = "insert into Persons(name, age, sex) values(?, ?, ?)";
        db.execSQL(SQLcammand, new String[]{person.getName(), ""+person.getAge(), person.getSex()});
        db.close();
    }

    public void deletePerson(String name){
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        String sql = "delete from person where name=?";
        db.execSQL(sql, new String[]{name});
        db.close();
    }

    public Cursor queryAllPerson(){
        String sql = "select * from person";
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public void updatePerson(Person person, int age){
        String sql = "update person set name=?, age=?, sex=? where age=?";
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        db.execSQL(sql, new String[]{person.getName(), person.getAge()+"", person.getSex(), age+""});
        db.close();
    }
}
