package com.example.ernestoramos.exma2gr15i04001;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lista extends AppCompatActivity {

    private ArrayAdapter adapter;
    public final int FACIL=3;
    public final int MEDIO=2;
    public final int DIFICIL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle("Puntajes");
            // getSupportActionBar().hide();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MainActivity.listaJugadores=Jugador.quickSort(MainActivity.listaJugadores, "Dificultad");

        ArrayList<Jugador> lstFacil=Recorrido( MainActivity.listaJugadores, FACIL);
        lstFacil=Jugador.quickSort(lstFacil, "Intentos");

        ArrayList<Jugador> lstMedio=Recorrido( MainActivity.listaJugadores, MEDIO);
        lstMedio=Jugador.quickSort(lstMedio, "Intentos");

        ArrayList<Jugador> lstDificl=Recorrido( MainActivity.listaJugadores, DIFICIL);
        lstDificl=Jugador.quickSort(lstDificl, "Intentos");

        MainActivity.listaJugadores.clear();
        MainActivity.listaJugadores.addAll(lstDificl);
        MainActivity.listaJugadores.addAll(lstMedio);
        MainActivity.listaJugadores.addAll(lstFacil);


        adapter = new AdaptadorJugadores(Lista.this,MainActivity.listaJugadores);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private ArrayList<Jugador> Recorrido(ArrayList<Jugador> lst, int param){
        ArrayList<Jugador> aux= new ArrayList<>();
        Iterator<Jugador> it = lst.iterator();
        while (it.hasNext()){
            Jugador j=it.next();
            if(j.dificultad==param){
                aux.add(j);
            }
        }
        return aux;
    }
}
