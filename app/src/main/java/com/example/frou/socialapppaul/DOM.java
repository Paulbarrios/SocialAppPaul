package com.example.frou.socialapppaul;

import android.content.Context;
import android.database.Cursor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Frou on 01/02/2015.
 */
public class DOM {

    private static String FICHERO = "articulosPaul.xml";

    public void guardarDatos(Cursor cursor, Context context){



        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

        DocumentBuilder constructor = null;
        try {
            constructor = fabrica.newDocumentBuilder();
            Document document = constructor.newDocument();

            Element raiz = document.createElement("articulos");
            document.appendChild(raiz);

            if (cursor.moveToFirst()) {
                do {
                    Element articulo = document.createElement("articulo");

                    Element id = document.createElement("id");
                    id.setTextContent(cursor.getInt(0)+"");
                    articulo.appendChild(id);

                    Element titulo = document.createElement("titulo");
                    titulo.setTextContent(cursor.getString(1));
                    articulo.appendChild(titulo);

                    Element descripcion = document.createElement("descripcion");
                    descripcion.setTextContent(cursor.getString(2));
                    articulo.appendChild(descripcion);

                    Element contenido = document.createElement("contenido");
                    contenido.setTextContent(cursor.getString(3));
                    articulo.appendChild(contenido);

                    raiz.appendChild(articulo);
                } while(cursor.moveToNext());
            }


            TransformerFactory transformerfactory=
                    TransformerFactory.newInstance();
            Transformer transformer=
                    transformerfactory.newTransformer();

            DOMSource source=new DOMSource(document);
            FileOutputStream _stream=context.openFileOutput(FICHERO, context.MODE_WORLD_WRITEABLE);
            StreamResult result=new StreamResult(_stream);
            transformer.transform(source, result);



        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void leerDatos(CRUD gestor, Context context){

        gestor.deleteAll();

        DocumentBuilderFactory fabrica2 = DocumentBuilderFactory.newInstance();

        DocumentBuilder constructor = null;
        try {
            constructor = fabrica2.newDocumentBuilder();

            Document document = constructor.parse(context.openFileInput(FICHERO));

            document.normalizeDocument();
            Node raiz = document.getDocumentElement();
            NodeList lista = raiz.getChildNodes();
            for (int i = 0, len = lista.getLength(); i < len; i++) {
                Node nodo = lista.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodo;
                    gestor.createArticulo(el.getElementsByTagName("titulo").item(0).getTextContent(), el.getElementsByTagName("descripcion").item(0).getTextContent(), el.getElementsByTagName("contenido").item(0).getTextContent());
                }
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
