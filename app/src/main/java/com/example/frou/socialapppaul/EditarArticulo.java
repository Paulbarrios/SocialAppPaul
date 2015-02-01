package com.example.frou.socialapppaul;


import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarArticulo extends Fragment {

    TextView titulo;
    TextView descripcion;
    TextView contenido;
    int id = 0;


    public EditarArticulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editar_articulo, container, false);

        titulo = (TextView) v.findViewById(R.id.tituloEditTextE);
        descripcion = (TextView) v.findViewById(R.id.descripcionEditTextE);
        contenido = (TextView) v.findViewById(R.id.contenidoEditTextE);

        CRUD gestor = new CRUD(getActivity().getApplicationContext());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }


        Cursor articulo = gestor.getArticulosById(id);
        articulo.moveToFirst();
        EditText tituloV = (EditText) v.findViewById(R.id.tituloEditTextE);
        EditText descripcionV = (EditText) v.findViewById(R.id.descripcionEditTextE);
        EditText contenidoV = (EditText) v.findViewById(R.id.contenidoEditTextE);

        tituloV.setText(articulo.getString(1));
        descripcionV.setText(articulo.getString(2));
        contenidoV.setText(articulo.getString(3));


        Button boton = (Button) v.findViewById(R.id.buttonEditar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloT = null;
                String descripcionT = null;
                String contenidoT = null;


                Boolean pass = true;
                if (titulo.getText() == null) {
                    pass = false;
                } else {
                    tituloT = titulo.getText().toString();
                }
                if (descripcion.getText() == null) {
                    pass = false;
                } else {
                    descripcionT = descripcion.getText().toString();
                }
                if (contenido.getText() == null) {
                    pass = false;
                } else {
                    contenidoT = contenido.getText().toString();
                }
                Toast tostada = Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT);
                if (pass == false) {
                    tostada.setText("Rellene todos lo campos");
                    tostada.show();
                } else {
                    CRUD gestor = new CRUD(getActivity().getApplicationContext());
                    if (gestor.updateArticulo(tituloT, descripcionT, contenidoT, id) > 0) {
                        tostada.setText("Se ha actualizado con exito");
                        tostada.show();
                    } else {
                        tostada.setText("Error al actualizar en la base de datos. Intentelo de nuevo");
                        tostada.show();
                    }

                }
            }
        });

        Button boton2 = (Button) v.findViewById(R.id.buttonVolver);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment verArticulo = new VerArticulo();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                verArticulo.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top).replace(R.id.content_frame, verArticulo).commit();

            }
        });

        return v;
    }


}
