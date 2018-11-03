package com.example.ernestoramos.exma2gr15i04001;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity implements View.OnClickListener {
    TextView lblDificultadElegida, lblPista, lblNick, lblIntentos, lblContador;
    ProgressBar Progress;
    Button btnProbarSuerte;
    EditText txtNumeroElegido;
    String Dificultad = "Fácil";
    Random r = new Random();
    int numeroAleatorio;
    public static Jugador j;
    public static int posicion;
    CountDownTimer mCountDownTimer;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        lblDificultadElegida = findViewById(R.id.lblDificultadElegida);
        lblPista = findViewById(R.id.lblPista);
        lblNick = findViewById(R.id.lblNick);
        lblIntentos = findViewById(R.id.lblIntentos);
        txtNumeroElegido = findViewById(R.id.txtNumeroElegido);
        btnProbarSuerte = findViewById(R.id.btnProbarSuerte);
        lblContador = findViewById(R.id.lblContador);
        Progress = findViewById(R.id.Progress);
        //Obteniendo posicion elegida por el usuario en los items de la lista
        posicion = getIntent().getIntExtra("Posicion", 0);
        //Creo un objeto en base a la posicion

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("¡Juguemos!");
            // getSupportActionBar().hide();
        }

        if (savedInstanceState != null) {
            //Validacion objeto
            if (savedInstanceState.getParcelable("Objeto") != null) {
                j = savedInstanceState.getParcelable("Objeto");
                lblIntentos.setText("Tus intentos son: " + j.intentos);
            } else {
                j = MainActivity.listaJugadores.get(posicion);
            }



            //Validacion iterator
            if (savedInstanceState.getInt("Auxiliar",-1) != -1) {
                i= savedInstanceState.getInt("Auxiliar");
            } else {
              i=0;
            }

            //Validacion numero aleatorio
            if (savedInstanceState.getInt("Numero", -1) != -1) {
                numeroAleatorio = savedInstanceState.getInt("Numero");
            } else {
                ConocerDificultad();
            }
        } else {
            j = MainActivity.listaJugadores.get(posicion);
            Progress.setProgress(0);
            i=0;
            //Validacion para conocer dificultad
            ConocerDificultad();
        }

        lblDificultadElegida.setText("Elegiste el modo " + Dificultad);
        lblNick.setText("¿Estás seguro? " + j.nick);
        //Lleno los parametros ya existentes
        btnProbarSuerte.setOnClickListener(this);
        final int milisegundos=j.tiempo/1000;


        mCountDownTimer=new CountDownTimer(j.tiempo,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                Progress.setProgress((int)i*100/milisegundos);
            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                Progress.setProgress(100);
                mCountDownTimer.cancel();
                Toast.makeText(getApplicationContext(), "Intento terminado", Toast.LENGTH_SHORT).show();
                finish();
            }

        };
        mCountDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownTimer.cancel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnProbarSuerte:
                if(!txtNumeroElegido.getText().toString().isEmpty()) {
                    int num = Integer.parseInt(txtNumeroElegido.getText().toString());
                    Evaluacion(num);
                }else{
                    Toast.makeText(getApplicationContext(), "Ingrese un numero", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ConocerDificultad() {
        if (j.dificultad == 1) {
            Dificultad = "Difícil";
            numeroAleatorio = r.nextInt(151 - 1) + 1;
            lblPista.setText("Pista: Me encuentro del 1 al 150");
        } else {
            if (j.dificultad == 2) {
                Dificultad = "Media";
                numeroAleatorio = r.nextInt(101 - 1) + 1;
                lblPista.setText("Pista: Me encuentro del 1 al 100");
            } else {
                Dificultad = "Fácil";
                numeroAleatorio = r.nextInt(51 - 1) + 1;
                lblPista.setText("Pista: Me encuentro del 1 al 50");
            }
        }
    }
    private  void Evaluacion(int num){
        if(num==numeroAleatorio){
            Toast.makeText(getApplicationContext(), "Felicidades, ganaste", Toast.LENGTH_LONG).show();
            j.intentos=j.intentos+1;
            MainActivity.listaJugadores.set(posicion, j);
            finish();
        }else{
            j.intentos=j.intentos+1;
            lblIntentos.setText("Tus intentos son: "+j.intentos);
        }
    }
    @Override
    protected  void onSaveInstanceState(Bundle guardarDatos){
        super.onSaveInstanceState(guardarDatos);
        //Guardamos la informacion
        guardarDatos.putInt("Numero", numeroAleatorio);
        guardarDatos.putParcelable("Objeto", j);
        guardarDatos.putInt("Auxiliar", i);
    }
}
