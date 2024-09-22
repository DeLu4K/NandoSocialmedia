package com.example.mytwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.editTextName);
        passwordEditText = findViewById(R.id.editTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress);


        Button buttonLogIn = findViewById(R.id.buttonLogIn);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        //INICIO DE SESION

       buttonLogIn.setOnClickListener(view ->{
           progressBar.setVisibility(View.VISIBLE);
           String userEmail = emailEditText.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(this.emailEditText.getText().toString(), this.passwordEditText.getText().toString()).addOnCompleteListener(this,task -> {
               if (task.isSuccessful()){
                   Toast.makeText(this, "Inicio de sesión correcto!", Toast.LENGTH_SHORT).show();
                   Usuario usuario = new Usuario();
                   usuario.setEmail(userEmail);
                   Intent paginaPrincipal = new Intent(MainActivity.this, PaginaPrincipal.class);
                   paginaPrincipal.putExtra("usuario", usuario);
                   startActivity(paginaPrincipal);
               }else{
                   FirebaseAuthException ex = (FirebaseAuthException) task.getException();
                   Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
               }
            });
        });

        //REGISTRO

        buttonSignUp.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String userEmail = emailEditText.getText().toString();
            firebaseAuth.createUserWithEmailAndPassword(this.emailEditText.getText().toString(), this.passwordEditText.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                            Usuario usuario = new Usuario();
                            usuario.setEmail(userEmail);
                            Intent paginaPrincipal = new Intent(MainActivity.this, PaginaPrincipal.class);
                            paginaPrincipal.putExtra("usuario", usuario);
                            startActivity(paginaPrincipal);
                            finish();
                        }
                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Log.w("create user: ERROR", task.getException());
                            Toast.makeText(this, "create user: ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }
}