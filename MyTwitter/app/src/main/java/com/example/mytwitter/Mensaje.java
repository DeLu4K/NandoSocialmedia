package com.example.mytwitter;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Serializable {
    private String usuario, texto;
    private int contadorMG;
    private LocalDateTime dateTime;
    private boolean isLiked;

    @SuppressLint("NewApi")
    public Mensaje(String usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
        this.contadorMG = 0;
        this.dateTime = LocalDateTime.now();
        this.isLiked = false;
    }

    @SuppressLint("NewApi")
    public Mensaje(String texto) {
        this.texto = texto;
        this.contadorMG = 0;
        this.dateTime = LocalDateTime.now();
        this.isLiked = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTexto() {
        return texto;
    }

    public int getContadorMG() {
        return contadorMG;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isLiked() {
        return isLiked;
    }
    public void incrementarContadorMG() {
        contadorMG++;
    }
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getFormattedDate() {
        @SuppressLint({"NewApi", "LocalSuppress"}) DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
