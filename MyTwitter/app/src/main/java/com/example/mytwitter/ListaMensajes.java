package com.example.mytwitter;

import java.util.ArrayList;

public class ListaMensajes {
    private ArrayList<Mensaje> lista = new ArrayList<>();
    public ListaMensajes(){
        this.lista.add(new Mensaje("ElGato@gmail.com", "Hola mundo"));
        this.lista.add(new Mensaje("ElGato@gmail.com", "Que tal?"));
        this.lista.add(new Mensaje("Pinocho@gmail.com", "Todo es mentira"));
        this.lista.add(new Mensaje("Dorothy@gmail.com", "Tacones?"));
        this.lista.add(new Mensaje("Antonia@gmail.com", "Mensaje"));

    }
    public ArrayList<Mensaje> getLista(){return lista;}
    public Mensaje get(int index){return lista.get(index);}
}
