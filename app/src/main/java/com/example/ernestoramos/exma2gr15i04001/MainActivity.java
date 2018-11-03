package com.example.ernestoramos.exma2gr15i04001;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Controles
    Button BtnIniciarJuego, BtnPuntajesAltos, BtnConfiguracion, BtnSalir;
    //Variables
    public static final  int JUGADORNUEVO =1;
    Jugador jug;
    public static ArrayList<Jugador> listaJugadores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos controles
        BtnIniciarJuego=findViewById(R.id.BtnIniciarJuego);
        BtnPuntajesAltos=findViewById(R.id.BtnPuntajesAltos);
        BtnConfiguracion=findViewById(R.id.BtnConfiguracion);
        BtnSalir=findViewById(R.id.BtnSalir);

        //Inicializamos variables adicionales
        listaJugadores=new ArrayList<>();
        if(savedInstanceState != null ){
            if (savedInstanceState.getParcelableArrayList("Lista") != null){
                listaJugadores=savedInstanceState.getParcelableArrayList("Lista");
            }
        }
        //Barra
        if(getSupportActionBar()!=null) {
           getSupportActionBar().setTitle("Bienvenido");
            // getSupportActionBar().hide();
        }


        //Activamos los listener
        BtnIniciarJuego.setOnClickListener(this);
        BtnPuntajesAltos.setOnClickListener(this);
        BtnConfiguracion.setOnClickListener(this);
        BtnSalir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnIniciarJuego:
                if(!listaJugadores.isEmpty()) {
                    Intent juego = new Intent(getApplicationContext(), Juego.class);
                    juego.putExtra("Posicion", buscar(jug));
                    startActivity(juego);
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor ingrese un jugador", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.BtnPuntajesAltos:
                Intent intPuntaje=new Intent(getApplicationContext(), Lista.class);
                startActivity(intPuntaje);
                break;
            case R.id.BtnConfiguracion:
                Intent intConfig=new Intent(getApplicationContext(), Configuracion.class);
                startActivityForResult(intConfig,JUGADORNUEVO);
                break;
            case R.id.BtnSalir:
                finish();
                break;
        }
    }

    public int buscar(Jugador j){
        int index = -1;
        int bound = listaJugadores.size();
        for (int userInd = 0; userInd < bound; userInd++) {
            if (listaJugadores.get(userInd).id==(j.id)) {
                index = userInd;
                break;
            }
        }
        return index;
    }

    @Override
    protected  void onSaveInstanceState(Bundle guardarDatos){
        super.onSaveInstanceState(guardarDatos);
        //Guardamos la informacion
        guardarDatos.putParcelableArrayList("Lista", listaJugadores);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case JUGADORNUEVO:
                if(data==null)return;
                String Nick = data.getExtras().getString("Nick");
                int Dificultad=data.getExtras().getInt("Dificultad");
                int Tiempo=data.getExtras().getInt("Tiempo");
                 jug = new Jugador(listaJugadores.size()+1,Nick,0,Dificultad, Tiempo);
                listaJugadores.add(jug);
                break;
        }
    }
}
