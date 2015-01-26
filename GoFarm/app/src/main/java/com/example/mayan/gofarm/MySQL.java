package com.example.mayan.gofarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mayan on 22/01/2015.
 */
class MySQL {

    public final String KEY_DATA = "key data";
    public final String KEY_ID = "id";
    public final String KEY_VALUE = "name";
    public final String DATABASE_NAME;
    public final String MY_NAME_TABLE = "name_my_table";
    public final int V = 4;

    final Context context;
    SQLiteDatabase db;
    MyDataBase myDataBase;

    public MySQL(Context context1, String data_base_name) {
        this.DATABASE_NAME = data_base_name;
        this.context = context1;
        myDataBase = new MyDataBase(context1);
    }

    private class MyDataBase extends SQLiteOpenHelper {

        public MyDataBase(Context context1) {
            super(context1, DATABASE_NAME, null, V);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists "+MY_NAME_TABLE+" ("
                    + KEY_ID +" integer not null primary key, "+KEY_VALUE+" REAL not null);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists "+MY_NAME_TABLE+";");
            onCreate(db);
        }
    }

    public void open() {
        db = myDataBase.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void destroyMe() {
        myDataBase.onUpgrade(db, V, V);
    }

    public long insertNewData(int id, double value) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_VALUE, value);

        return db.insert(MY_NAME_TABLE, null, values);
    }

    public Cursor getAllValues() {
        return db.query(MY_NAME_TABLE, new String[]{KEY_ID,KEY_VALUE},null,null,null,null,null);
    }

}