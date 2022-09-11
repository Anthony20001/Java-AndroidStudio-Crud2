package com.example.investigacion.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyHelperSqlLite extends SQLiteOpenHelper {

    public MyHelperSqlLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users (id integer primary key AUTOINCREMENT, username text, email text, password text, isAdmin integer CHECK(isAdmin in (0,1)))");

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
