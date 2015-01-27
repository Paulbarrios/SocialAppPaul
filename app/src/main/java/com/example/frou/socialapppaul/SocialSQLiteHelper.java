package com.example.frou.socialapppaul;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Frou on 26/01/2015.
 */
public class SocialSQLiteHelper extends SQLiteOpenHelper{

    private String sqlCreate1 = "CREATE TABLE \"articulos\" (\"id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"titulo\" CHAR, \"descripcion\" CHAR, \"contenido\" TEXT)";

    public SocialSQLiteHelper(Context contexto, String nombre,
                              CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL(sqlCreate1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS articulos");

        db.execSQL(sqlCreate1);
    }


}
