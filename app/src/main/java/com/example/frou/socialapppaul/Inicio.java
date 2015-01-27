package com.example.frou.socialapppaul;


import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        SocialSQLiteHelper usdbh =
                new SocialSQLiteHelper(getActivity().getApplicationContext(), "DBSocial", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
/*        if(db != null) {
            //Insertamos 5 usuarios de ejemplo
            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario" + i;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre + "')");
            }

            //Cerramos la base de datos

        }*/

        db.close();


        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }


}
