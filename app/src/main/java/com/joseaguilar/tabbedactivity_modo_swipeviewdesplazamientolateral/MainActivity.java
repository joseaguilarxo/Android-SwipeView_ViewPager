package com.joseaguilar.tabbedactivity_modo_swipeviewdesplazamientolateral;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.joseaguilar.tabbedactivity_modo_swipeviewdesplazamientolateral.Fragments.Fragment_Azul;
import com.joseaguilar.tabbedactivity_modo_swipeviewdesplazamientolateral.Fragments.Fragment_Rojo;
import com.joseaguilar.tabbedactivity_modo_swipeviewdesplazamientolateral.Fragments.Fragment_Verde;

//Paso1: implementar los fragment
public class MainActivity extends AppCompatActivity implements
Fragment_Rojo.OnFragmentInteractionListener,
Fragment_Azul.OnFragmentInteractionListener,
Fragment_Verde.OnFragmentInteractionListener{


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;
//----------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    //----------------------------------------------------------------------------------------------------------------------------
//APARTADO DEL MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //----------------------------------------------------------------------------------------------------------------------------
//ESTE ES EL APARTADO QUE NOS INTERESA PARA PODER PROGRAMAR LA SECUENCIA DE TRASLACION
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        //PASO 2: ESTE ES EL METODO QUE NOS INTERESA
        /*
        * Al crear esta actividad, su estructura sera la siguiente:
        *
        * public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        *
        * Pero nosotros la configuraremos de tal forma que el resultado para poder trabajarla quede asi: */
        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment = null;
            switch (sectionNumber) {
                case 1:
                    fragment = new Fragment_Rojo();
                    break;
                case 2:
                    fragment = new Fragment_Azul();
                    break;
                case 3:
                    fragment = new Fragment_Verde();
                    break;
            }
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        //ESTE GETCOUNT SE MODIFICA Y SE COLOCA EL NUMERO DE LOS FRAGMENTS QUE VAYAMOS A COLOCAR
        @Override
        public int getCount() {
            return 3;
        }

        //ESTE APARTADO SOLO LO USARIAMOS EN CASO USEMOS UNA PAGINACION (tabs), EN ESTE CASO NO
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
        //----------------------------------------------------------------------------------------------------------------------------

    }
}
