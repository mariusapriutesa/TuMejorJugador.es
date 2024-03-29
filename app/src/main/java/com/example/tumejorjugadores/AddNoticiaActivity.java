package com.example.tumejorjugadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoticiaActivity extends AppCompatActivity {
    //creando variables para su botón, edite texto, base de datos firebase, referencia de base de datos, barra de progreso( la animacion).
    private Button addJugadorBtn;
    private TextInputEditText jugadorNameEdt;
    private TextInputEditText jugadorDescEdt;
    private TextInputEditText jugadorImgEdt;
    private TextInputEditText jugadorLinkEdt;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ProgressBar loadingPB;
    private String jugadorID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_jugador);
        //initializando todas las variables.
        addJugadorBtn = findViewById(R.id.idBtnAddJugador);

        jugadorNameEdt = findViewById(R.id.idEdtJugadorName);
        jugadorDescEdt = findViewById(R.id.idEdtJugadorDescription);
        //  jugadorFechaEdt = findViewById(R.id.idEdtJugadorDescription);
        jugadorImgEdt = findViewById(R.id.idEdtJugadorImageLink);
        jugadorLinkEdt = findViewById(R.id.idEdtJugadorLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //abajo creamos nuestra referencia a la base de datos.
        databaseReference = firebaseDatabase.getReference("Jugadores");
        //agregando onclickListener para nuestro botón de agregar noticias.
        addJugadorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);


                //obtener datos de nuestro edit text.
                String jugadorName = jugadorNameEdt.getText().toString();
                String jugadorDesc = jugadorDescEdt.getText().toString();
                String jugadorFecha = new SimpleDateFormat("yyyy/MM/dd  HH:mm", Locale.getDefault()).format(new Date());
                String jugadorImg = jugadorImgEdt.getText().toString();
                String jugadorLink = jugadorLinkEdt.getText().toString();
                jugadorID = jugadorName;
                //en la línea de abajo estamos pasando todos los datos a nuestra clase jugadorRVModal.
                NoticiaRVModal noticiaRVModal = new NoticiaRVModal( jugadorName, jugadorDesc, jugadorFecha,  jugadorImg, jugadorLink,jugadorID);
                //en la línea de abajo estamos llamando a un evento de valor agregado para pasar datos a la base de datos de firebase.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //en la línea de abajo estamos configurando datos en nuestra base de datos de firebase.
                        databaseReference.child(jugadorID).setValue(noticiaRVModal);
                        //displaying a toast message.
                        Toast.makeText(AddNoticiaActivity.this, "Noticia Añadida..", Toast.LENGTH_SHORT).show();
                        //starting el main activity.
                        startActivity(new Intent(AddNoticiaActivity.this, com.example.tumejorjugadores.MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //mostrando un mensaje de error en la línea de abajo.
                        Toast.makeText(AddNoticiaActivity.this, "Error en añadir la noticia..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}