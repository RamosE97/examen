package com.example.ernestoramos.exma2gr15i04001;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorJugadores extends ArrayAdapter<Jugador> {
    public AdaptadorJugadores(@NonNull Context context, List<Jugador> object) {
        super(context, 0,object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Jugador jugador = getItem(position);
            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jugador, parent, false);
            }

            ImageView Img = convertView.findViewById(R.id.Img);
            TextView lblNombre = convertView.findViewById(R.id.lblNombre);
            TextView lblPuntos = convertView.findViewById(R.id.lblPuntos);
            TextView lblDificultad = convertView.findViewById(R.id.lblDificultad);
            TextView lblTiempo=convertView.findViewById(R.id.lblTiempo);
            Img.setImageResource(R.drawable.ic_person_black_24dp);
            lblNombre.setText(jugador.nick);
            String dificultad = "Fácil";
            switch (jugador.dificultad) {
                case 1:
                    dificultad = "Difícil";
                    break;
                case 2:
                    dificultad = "Medio";
                    break;
                case 3:
                    dificultad = "Fácil";
                    break;

            }
            String tiempo= "1 minuto";
        switch (jugador.tiempo) {
            case 60000:
                tiempo = "Tiempo: 1 minuto";
                break;
            case 300000:
                tiempo = "Tiempo: 5 minutos";
                break;
            case 600000:
                tiempo = "Tiempo: 10 minutos";
                break;

        }
            lblDificultad.setText("Dificultad: " + dificultad);
            lblTiempo.setText(tiempo);
            lblPuntos.setText("Intentos: " + String.valueOf(jugador.intentos));
        return convertView;
    }
}
