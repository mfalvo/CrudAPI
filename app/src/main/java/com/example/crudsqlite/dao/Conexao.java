package com.example.crudsqlite.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Conexao extends SQLiteOpenHelper{

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb){
        String query = "create table pessoa(id integer primary key autoincrement, nome varchar(50), "+
                "email varchar(50), phone varchar(50))";
        sqLiteDb.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteData, int op1, int opt2)
    {

    }
}
