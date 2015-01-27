package com.example.frou.socialapppaul;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class Inicio extends Fragment {


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       CRUD gestor = new CRUD(getActivity().getApplicationContext());

        gestor.createArticulo("Titulo", "Descripcion", "Contenido");
        gestor.createArticulo("Titulo2", "Descripcion2", "Contenido2");
        gestor.updateArticulo("Titulo", "Descripcion", "Contenido", 3);
        gestor.deleteArticulo(1);
        Cursor c = gestor.getAllArticulos();

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String id= c.getString(0);
                String titulo = c.getString(1);
                String descripcion= c.getString(2);
                String contenido = c.getString(3);
                Log.e("TAG", id);
                Log.e("TAG", titulo);
                Log.e("TAG", descripcion);
                Log.e("TAG", contenido);

            } while(c.moveToNext());
        }


        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }


}
