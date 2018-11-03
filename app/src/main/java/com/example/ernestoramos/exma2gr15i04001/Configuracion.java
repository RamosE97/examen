package com.example.ernestoramos.exma2gr15i04001;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Configuracion extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Button btnAceptar, btnCancelar;
    EditText txtNickname;
    RadioButton rbDificil, rbMedio, rbFacil, rb1Minuto, rb5Minuto, rb10Minuto;
    RadioGroup rdG, rdTiempo;
    int Dificultad = 3;
    int Tiempo = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Inicializamos variables
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtNickname = findViewById(R.id.txtNickname);
        rdG = findViewById(R.id.rdG);
        rbFacil = findViewById(R.id.rbFacil);
        rbMedio = findViewById(R.id.rbMedio);
        rbDificil = findViewById(R.id.rbDificil);
        rb1Minuto = findViewById(R.id.rb1Minuto);
        rb5Minuto = findViewById(R.id.rb5Minuto);
        rb10Minuto = findViewById(R.id.rb10Minuto);
        rdTiempo=findViewById(R.id.rdTiempo);
        rb1Minuto.setChecked(true);
        rbFacil.setChecked(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Configuración");
            // getSupportActionBar().hide();
        }

        //Listener
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        rdG.setOnCheckedChangeListener(this);
        rdTiempo.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptar: {
                if (!txtNickname.getText().toString().isEmpty()) {
                    String Nick = txtNickname.getText().toString();
                    Intent resultado = new Intent();
                    resultado.putExtra("Nick", Nick);
                    resultado.putExtra("Dificultad", Dificultad);
                    resultado.putExtra("Tiempo", Tiempo);
                    setResult(MainActivity.JUGADORNUEVO, resultado);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.IngresaNick), Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.btnCancelar:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup.getId() == R.id.rdG) {
            switch (i) {
                case R.id.rbFacil:
                    Dificultad = 3;
                    break;
                case R.id.rbMedio:
                    Dificultad = 2;
                    break;
                case R.id.rbDificil:
                    Dificultad = 1;
                    break;
            }
        } else {
            switch (i) {
                case R.id.rb1Minuto:
                    Tiempo = 60000;
                    break;
                case R.id.rb5Minuto:
                    Tiempo = 300000;
                    break;
                case R.id.rb10Minuto:
                    Tiempo = 600000;
                    break;
            }
        }
    }
}
