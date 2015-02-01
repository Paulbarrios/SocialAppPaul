package com.example.frou.socialapppaul;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bloquear el giro
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //recuperar los datos si se ha destruido la actividad.

        //Asignar la toolbar como action bar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        //Usar un array para la lista del navigation drawer
        ListView navList = (ListView) findViewById(R.id.left_drawer);
        final String[] names = getResources().getStringArray(R.array.nav_options);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        navList.setAdapter(adapter);


        //listener para que el fragmento cambie con el navigation drawer
        navList.setOnItemClickListener(new SlideMenuClickListener());

        //marcar el primer elemento del navigation drawer al iniciar.
        navList.setSelection(0);
        displayView(0);

    }




    //listener para el navigation drawer
    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }



    // muestra el view seleccionado (lo llama el listener del navigation drawer).
    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Inicio();
                break;
            case 1:
                fragment = new AddArticulo();
                break;

        }



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top).replace(R.id.content_frame, fragment).commit();

        // cerrar el drawer, ponerle titulo, etc.
        DrawerLayout miDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView listaNav = (ListView) findViewById(R.id.left_drawer);
        String[] listaTitulos = getResources().getStringArray(R.array.nav_options);
        listaNav.setItemChecked(position, true);
        listaNav.setSelection(position);
        setTitle(listaTitulos[position]);
        miDrawerLayout.closeDrawer(listaNav);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        return super.onOptionsItemSelected(item);
    }
}
