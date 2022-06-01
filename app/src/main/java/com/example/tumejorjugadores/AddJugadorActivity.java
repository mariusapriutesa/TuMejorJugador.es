package com.example.tumejorjugadores;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddJugadorActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    //creando variables para su botón, edite texto, base de datos firebase, referencia de base de datos, barra de progreso.
    private Button addJugadorBtn;
    private TextInputEditText jugadorNameEdt, jugadorDescEdt,  jugadorFechaEdt, jugadorImgEdt, jugadorLinkEdt;
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
        jugadorFechaEdt = findViewById(R.id.idEdtJugadorFecha);
        jugadorImgEdt = findViewById(R.id.idEdtJugadorImageLink);
        jugadorLinkEdt = findViewById(R.id.idEdtJugadorLink);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //abajo creamos nuestra referencia a la base de datos.
        databaseReference = firebaseDatabase.getReference("Jugadores");
        //agregando click listener para nuestro botón de agregar jugador.

        Calendar calendar =Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day= calendar.get(Calendar.DAY_OF_MONTH);


        jugadorFechaEdt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DatePickerDialog  datePickerDialog= new DatePickerDialog(
                        AddJugadorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date= day+"/"+month+"/"+year;
                        jugadorFechaEdt.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }




        });

        addJugadorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);


                //obtener datos de nuestro texto de edición.
                String jugadorName = jugadorNameEdt.getText().toString();
                String jugadorDesc = jugadorDescEdt.getText().toString();
                String jugadorFecha = jugadorFechaEdt.getText().toString();
                String jugadorImg = jugadorImgEdt.getText().toString();
                String jugadorLink = jugadorLinkEdt.getText().toString();
                jugadorID = jugadorName;
                //en la línea de abajo estamos pasando todos los datos a nuestra clase modal.
                JugadorRVModal jugadorRVModal = new JugadorRVModal(jugadorID, jugadorName, jugadorDesc, jugadorFecha,  jugadorImg, jugadorLink);
                //en la línea de abajo estamos llamando a un evento de valor agregado para pasar datos a la base de datos de firebase.
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //en la línea de abajo estamos configurando datos en nuestra base de datos de firebase.
                        databaseReference.child(jugadorID).setValue(jugadorRVModal);
                        //displaying a toast message.
                        Toast.makeText(AddJugadorActivity.this, "Noticia Añadida..", Toast.LENGTH_SHORT).show();
                        //starting el main activity.
                        startActivity(new Intent(AddJugadorActivity.this, com.example.tumejorjugadores.MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        //mostrando un mensaje de error en la línea de abajo.
                        Toast.makeText(AddJugadorActivity.this, "Error en añadir la noticia..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}