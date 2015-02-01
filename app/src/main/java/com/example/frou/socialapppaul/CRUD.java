package com.example.frou.socialapppaul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by Frou on 27/01/2015.
 */
public class CRUD {

    private SocialSQLiteHelper socialSQLiteHelper;
    private SQLiteDatabase db;
    private static final String DATA_BASE = "DBSocial";
    private static final String TABLE_ARTICULOS = "articulos";

    public CRUD(Context context){

        socialSQLiteHelper = new SocialSQLiteHelper(context, DATA_BASE, null, 1);

        db = socialSQLiteHelper.getWritableDatabase();
    }

    public long createArticulo(String titulo, String descripcion, String contenido){
        ContentValues values = new ContentValues();

        values.put("titulo",titulo);
        values.put("descripcion",descripcion);
        values.put("contenido",contenido);

        return db.insert(TABLE_ARTICULOS,null,values);
    }

    public long updateArticulo(String titulo, String descripcion, String contenido, int id){

        ContentValues valores = new ContentValues();
        valores.put("titulo",titulo);
        valores.put("descripcion",descripcion);
        valores.put("contenido",contenido);


        return db.update(TABLE_ARTICULOS, valores, "_id="+id, null);
    }

    public long deleteArticulo(int id){

        return db.delete(TABLE_ARTICULOS, "_id=" + id, null);
    }

    public Cursor getAllArticulos(){

        return db.rawQuery("SELECT * FROM "+TABLE_ARTICULOS, null);
    }

    public Cursor getArticulosById(int id){
        Log.e("TAG", "SELECT * FROM "+TABLE_ARTICULOS+" WHERE _id="+id);
        return db.rawQuery("SELECT * FROM "+TABLE_ARTICULOS+" WHERE _id="+id, null);
    }
}
