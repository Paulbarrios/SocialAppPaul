package com.example.frou.socialapppaul;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddArticulo extends Fragment {

    TextView titulo;
    TextView descripcion;
    TextView contenido;


    public AddArticulo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_articulo, container, false);

        titulo = (TextView) v.findViewById(R.id.tituloEditText);
        descripcion = (TextView) v.findViewById(R.id.descripcionEditText2);
        contenido = (TextView) v.findViewById(R.id.contenidoEditText3);

        Button loginButton = (Button) v.findViewById(R.id.buttonAdd);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloT = null;
                String descripcionT = null;
                String contenidoT = null;



                Boolean pass = true;
                if(titulo.getText() == null){
                    pass = false;
                }else{
                    tituloT = titulo.getText().toString();
                }
                if(descripcion.getText() == null){
                    pass = false;
                }else{
                    descripcionT = descripcion.getText().toString();
                }
                if(contenido.getText() == null){
                    pass = false;
                }else{
                    contenidoT = contenido.getText().toString();
                }
                Toast tostada = Toast.makeText(getActivity().getApplicationContext(),"",Toast.LENGTH_SHORT);
                if(pass == false){
                    tostada.setText("Rellene todos lo campos");
                    tostada.show();
                }else{
                    CRUD gestor = new CRUD(getActivity().getApplicationContext());
                    if(gestor.createArticulo(tituloT, descripcionT,contenidoT) > 0){
                        tostada.setText("Se ha guardado con exito");
                        tostada.show();
                        titulo.setText("");
                        contenido.setText("");
                        descripcion.setText("");
                    }else{
                        tostada.setText("Error al guardar en la base de datos. Intentelo de nuevo");
                        tostada.show();
                    }

                }
            }
        });



        return v;
    }


}
