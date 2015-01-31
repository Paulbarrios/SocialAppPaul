package com.example.frou.socialapppaul;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.acl.LastOwnerException;

/**
 * Created by Frou on 27/01/2015.
 */
public class ArticulosAdapter extends CursorAdapter {

    public ArticulosAdapter(Context context, Cursor cursor){
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_adapter_view, null, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titulo = (TextView)view.findViewById(R.id.titulo);
        titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));

        TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
        descripcion.setText(cursor.getString(cursor.getColumnIndex("descripcion")));
    }
}
