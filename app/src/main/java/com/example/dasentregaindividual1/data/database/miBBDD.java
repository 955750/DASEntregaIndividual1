package com.example.dasentregaindividual1.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class miBBDD extends SQLiteOpenHelper {


    public miBBDD(
        @Nullable Context context,
        @Nullable String name,
        @Nullable SQLiteDatabase.CursorFactory factory,
        int version
    ) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE Usuarios (" +
                "'Codigo' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " 'Nombre' VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
