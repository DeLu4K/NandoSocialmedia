package com.example.mytwitter;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private String email;
    private ArrayList<Mensaje> mensajesPublicados;

    public Usuario() {
        this.mensajesPublicados = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Mensaje> getMensajesPublicados() {
        return mensajesPublicados;
    }
    public void addMensajePublicado(Mensaje mensaje){
        mensajesPublicados.add(mensaje);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
