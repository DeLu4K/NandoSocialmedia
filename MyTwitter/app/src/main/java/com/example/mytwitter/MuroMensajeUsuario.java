package com.example.mytwitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MuroMensajeUsuario extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MensajeAdapterRV adapterRV;
    Usuario usuario;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muro_mensaje_usuario);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        if (usuario != null) {

            recyclerView = findViewById(R.id.recyclerViewMuroUsuario);
            adapterRV = new MensajeAdapterRV(usuario.getMensajesPublicados());
            recyclerView.setAdapter(adapterRV);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } else {
            Toast.makeText(this, "Error: Usuario no conectado", Toast.LENGTH_SHORT).show();
            finish();
        }
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuroMensajeUsuario.this, PaginaPrincipal.class);
                startActivity(intent);


            }
        });

    }

}

