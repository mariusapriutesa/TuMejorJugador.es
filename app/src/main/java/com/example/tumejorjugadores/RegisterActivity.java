package com.example.tumejorjugadores;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    //creando variables para edittext y textview, firebase auth, button y barra de progreso.
    private TextInputEditText userNameEdt,confirmPwdEdt,passwordEdt;
    //*********
    private String usuarioId;
    private String usuarioImg;
    private String rolEdt;

    private TextView loginTV;
    FirebaseDatabase firebaseDatabase;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // inicializando todas nuestras variables.
        userNameEdt = findViewById(R.id.idEdtUserName);
        //****
        usuarioImg="sda";
        rolEdt = "Usuario";
        firebaseDatabase = FirebaseDatabase.getInstance();
        //abajo creamos nuestra referencia a la base de datos.




        passwordEdt = findViewById(R.id.idEdtPassword);
        loadingPB = findViewById(R.id.idPBLoading);
        confirmPwdEdt = findViewById(R.id.idEdtConfirmPassword);
        loginTV = findViewById(R.id.idTVLoginUser);
        registerBtn = findViewById(R.id.idBtnRegister);
        mAuth = FirebaseAuth.getInstance();

        //agragando click para login tv.


        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abriendo el login activity con clicking login text.
                Intent i = new Intent(RegisterActivity.this, com.example.tumejorjugadores.LoginActivity.class);
                startActivity(i);
            }
        });



        //agregando click listener para register button.
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ocultar nuestra barra de progreso.
                loadingPB.setVisibility(View.VISIBLE);
                // obteniendo datos  de  su edit text..
                String userName = userNameEdt.getText().toString();
                String pwd = passwordEdt.getText().toString();
                String cnfPwd = confirmPwdEdt.getText().toString();

                //***
               FirebaseDatabase database=firebaseDatabase;
                String userName2=userName.replace("@","0");
                userName2 = userName2.replace(".","0");
                usuarioId =userName2;
                String rolEdt="Usuario";
                usuarioImg="sda";
                //comprobando si la contraseña y la contraseña de confirmación son iguales o no.

                if (!pwd.equals(cnfPwd)) {
                    Toast.makeText(RegisterActivity.this, "Porfavor compruebe si ambos contraseñas son iguales..", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)) {
                    //comprobando si los campos de texto están vacíos o no.
                    Toast.makeText(RegisterActivity.this, "Por favor ingrese sus credenciales..", Toast.LENGTH_SHORT).show();
                } else {
                    // en la línea de abajo estamos creando un nuevo usuario al pasar el correo electrónico y la contraseña.
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // en la línea de abajo estamos comprobando si la tarea es exitosa o no.
                            if (task.isSuccessful()) {
                                UsuarioRVModal usuarioRVModal = new UsuarioRVModal(usuarioId,userName, usuarioImg,passwordEdt.getText().toString(), rolEdt);
                                loadingPB.setVisibility(View.GONE);

                                firebaseDatabase.getReference("Usuarios").child(String.valueOf(usuarioId)).setValue(usuarioRVModal);

                                Toast.makeText(RegisterActivity.this, "Usuario Registrado..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                Log.i("koala","soy 3");

                                //en otra condición, estamos mostrando un toast mesaje de falla.
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "No se ha podido registrar el usuario..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}


































