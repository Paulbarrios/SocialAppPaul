package com.example.frou.socialapppaul;


import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


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

        View v = inflater.inflate(R.layout.fragment_inicio, container, false);
        CRUD gestor = new CRUD(getActivity().getApplicationContext());

//       for(int i = 1; i < 12; i++){
//           gestor.createArticulo("Titulo "+i, "Descripcion "+i, "Contenido");
//       }




        ArticulosAdapter adapter = new ArticulosAdapter(getActivity().getApplicationContext(), gestor.getAllArticulos());
        ListView lista = (ListView) v.findViewById(R.id.listView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                Log.d("TAG", "clicked on item: " + cursor.getInt(0));


            }
        });

        return v;
    }


}
