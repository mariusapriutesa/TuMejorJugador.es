package com.example.tumejorjugadores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NavHeaderActivity extends AppCompatActivity {

    TextView txtEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navheader);
        txtEmail = (TextView) findViewById(R.id.UsuarioEmail);
        txtEmail.setText("hola");
    }


}