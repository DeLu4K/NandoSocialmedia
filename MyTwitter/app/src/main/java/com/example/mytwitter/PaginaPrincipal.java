package com.example.mytwitter;

import android.annotation.SuppressLint;
import android.app.Dialog;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class PaginaPrincipal extends AppCompatActivity implements MensajeAdapterRV.OnButtonClickListener {
    private RecyclerView recyclerView;
    private MensajeAdapterRV adapterRV;
    private ListaMensajes listaMensajes = new ListaMensajes();
    private NavigationView navigationView;
    private Usuario usuario;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");


        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (usuario != null) {
            String userEmail = usuario.getEmail();
            setTitle(userEmail);
        }else{
            setTitle("Página Principal");
        }


        recyclerView = findViewById(R.id.recyclerView);
        adapterRV = new MensajeAdapterRV(listaMensajes.getLista());

        adapterRV.setOnButtonClickListener(this);

        recyclerView.setAdapter(adapterRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton addMensaje = findViewById(R.id.addM);
        addMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PaginaPrincipal.this);
                dialog.setContentView(R.layout.add_mensaje_dialogo);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(window.getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);

                Button publicar = dialog.findViewById(R.id.buttonPublicar);
                publicar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText publicacion = dialog.findViewById(R.id.editTextText);

                        String usuario = ((Usuario) getIntent().getSerializableExtra("usuario")).getEmail();
                        String publi = publicacion.getText().toString();
                        if (!publi.isEmpty()) {
                            newMensaje(usuario, publi);
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.YourMensajes) {
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent2 = new Intent(this, MuroMensajeUsuario.class);
                intent2.putExtra("usuario", usuario);
                startActivity(intent2);
                return true;
            }
            return true;
        });
    }

    public void newMensaje(String m, String publi) {
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        Mensaje nuevo = new Mensaje(m,publi);
        listaMensajes.getLista().add(nuevo);
        if (usuario != null){
            usuario.addMensajePublicado(nuevo);
        }
        adapterRV.notifyItemInserted(listaMensajes.getLista().size() - 1);

    }


    @Override
    public void onButtonClicked(int position) {

    }

    @Override
    public void onLongButtonClicked(int position) {
        listaMensajes.getLista().remove(listaMensajes.get(position));
        adapterRV.notifyDataSetChanged();

    }

}