package com.example.ernestoramos.exma2gr15i04001;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.internal.ParcelableSparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Parcelable {
    int id;
    String nick;
    int intentos;
    int dificultad;
    int tiempo;

    Jugador(int i, String n, int intentos, int d, int t){
        this.id=i;
        this.nick=n;
        this.intentos=intentos;
        this.dificultad=d;
        this.tiempo=t;
    }

    private Jugador(Parcel in) {
        this.id = in.readInt();
        this.nick = in.readString();
        this.intentos = in.readInt();
        this.dificultad = in.readInt();
        this.tiempo=in.readInt();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeInt(intentos);
        out.writeInt(dificultad);
        out.writeString(nick);
        out.writeInt(tiempo);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<Jugador> CREATOR = new Parcelable.Creator<Jugador>() {
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public int compararElementos(Jugador j, String Parametro) {
        int resultado=0;
        if(Parametro.contains("Intentos")) {
            if (this.intentos < j.intentos)
                resultado = -1;
             else
                 resultado = 1;
        }else{
            if (this.dificultad < j.dificultad)
                resultado = -1;
            else
                resultado = 1;
        }
        return resultado;
    }

    public static ArrayList<Jugador> quickSort(ArrayList<Jugador> list, String Parametro)
    {
        if (list.size() <= 1)
            return list;

        ArrayList<Jugador> menor = new ArrayList<>();
        ArrayList<Jugador> greater = new ArrayList<>();
        Jugador pivot = list.get(list.size()-1);
        for (int i = 0; i < list.size()-1; i++)
        {
            if (list.get(i).compararElementos(pivot, Parametro) < 0)
                menor.add(list.get(i));
            else
                greater.add(list.get(i));
        }
        menor = quickSort(menor, Parametro);
        greater = quickSort(greater, Parametro);
        menor.add(pivot);
        menor.addAll(greater);
        list = menor;
        return list;
    }
}
