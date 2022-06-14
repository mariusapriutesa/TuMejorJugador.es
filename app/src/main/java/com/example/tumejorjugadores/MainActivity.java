package com.example.tumejorjugadores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements com.example.tumejorjugadores.JugadorRVAdapter.JugadorClickInterface {

    //creando variables para fab, firebase database, progress bar, list, adapter,firebase auth, recycler view and relative layout.
    private FloatingActionButton addJugadorFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference mDatabase;
    //****tutorial



    FirebaseAuth auth;
    private RecyclerView jugadorRV;//eee
    private SearchView searchView;///eee
    private ProgressBar loadingPB;
    private ArrayList<JugadorRVModal> jugadorRVModalArrayList;
    private TextView usuarioName;
    private JugadorRVAdapter jugadorRVAdapter;
    private RelativeLayout homeRL;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        // Show status bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        usuarioName = nav.getHeaderView(0).findViewById(R.id.UsuarioEmail);
        mDatabase=FirebaseDatabase.getInstance().getReference();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
                        String userEmail = user.getEmail();
                        usuarioName.setText(userEmail);
                    } else {
                        // No user is signed in
                    }


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.idLogOut:
                        //mostrando a toast message en el usuario cerró la sesión en el interior al hacer clic.
                        Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_LONG).show();
                        // En la línea inferior estamos cerrando la sesión de nuestra usuaria.
                        auth.signOut();
                        // en la línea de abajo estamos abriendo nuestra actividad de inicio de sesión.
                        Intent i = new Intent(MainActivity.this, com.example.tumejorjugadores.LoginActivity.class);
                        startActivity(i);
                        return true;

                }

                return true;
            }
        });


        //initializing all our variables.
        jugadorRV = findViewById(R.id.idRVJugadores);
        homeRL = findViewById(R.id.idRLBSheet);
        loadingPB = findViewById(R.id.idPBLoading);
        addJugadorFAB = findViewById(R.id.idFABAddJugador);
        searchView= findViewById(R.id.search_view);

        searchView.clearFocus();
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        jugadorRVModalArrayList = new ArrayList<JugadorRVModal>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String strSearch) {
                filterlist(strSearch);
                return true;
            }
        });

        // en la línea de abajo estamos obteniendo la referencia de la base de datos.
        databaseReference = firebaseDatabase.getReference("Jugadores");

        // En la línea de abajo agregando un click listener para nuestro floating action button.
        addJugadorFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar**
                //abrir una nueva actividad para agregar un jugador.
                Intent i = new Intent(MainActivity.this, AddJugadorActivity.class);
                startActivity(i);
            }
        });

        // en la línea de abajo inicializando nuestra clase de adaptador.
        jugadorRVAdapter = new JugadorRVAdapter(jugadorRVModalArrayList, this, this::onJugadorClick);
        // configurando el simulador de diseño para la vista del reciclador en la línea de abajo.
        jugadorRV.setLayoutManager(new LinearLayoutManager(this));
        //configurando el adaptador a recycler view abajo.
        jugadorRV.setAdapter(jugadorRVAdapter);
        // en la línea de abajo llamando a un método para obtener jugadores de la base de datos.
        getJugadores();
    }

    private void getJugadores() {
        // en la línea de abajo limpiando nuestra lista.
        jugadorRVModalArrayList.clear();
        // en la línea de abajo estamos llamando al método add child event listener para leer los datos.
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //en la línea de abajo estamos ocultando nuestra barra de progreso.
                loadingPB.setVisibility(View.GONE);
                // agregando una instantánea a nuestra lista de matrices en la línea de abajo.
                jugadorRVModalArrayList.add(snapshot.getValue(JugadorRVModal.class));
                //notificando a nuestro adaptador que los datos han cambiado.
                jugadorRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //este método se llama cuando se agrega un nuevo hijo, estamos notificando a nuestro adaptador y haciendo que la visibilidad de la barra de progreso desaparezca.
                loadingPB.setVisibility(View.GONE);
                jugadorRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // notificando a nuestro adaptador cuando se elimine el niño.
                jugadorRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // notificando a nuestro adaptador cuando se mueve al niño.
                jugadorRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onJugadorClick(int position) {
        // llamando a un método para mostrar una hoja inferior en la línea de abajo.
        displayBottomSheet(jugadorRVModalArrayList.get(position));
    }
    private void displayBottomSheet(JugadorRVModal modal) {
        // en la línea de abajo estamos creando nuestro cuadro de diálogo de hoja inferior.
        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        //en la línea de abajo estamos inflando un archivo de diseño para su hoja inferior.
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, homeRL);
        // configurando la vista de contenido para la hoja inferior en la línea de abajo.
        bottomSheetTeachersDialog.setContentView(layout);
        // en la línea de abajo estamos configurando un cancelable
        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        //llamando a un método para mostrar nuestra última hoja.
        bottomSheetTeachersDialog.show();
        // en la línea de abajo estamos creando variables para nuestra vista de texto y vista de imagen dentro de la hoja inferior
        //e inicializarlos con sus identificaciones.
        TextView jugadorNameTV = layout.findViewById(R.id.idTVJugadorName);
        TextView jugadorDescTV = layout.findViewById(R.id.idTVJugadorDesc);

        TextView fechaTV = layout.findViewById(R.id.idTVJugadorFecha);
        ImageView jugadorIV = layout.findViewById(R.id.idIVJugador);
        // en la línea de abajo estamos configurando datos para diferentes vistas en la línea de abajo.
        jugadorNameTV.setText(modal.getJugadorName());
        jugadorDescTV.setText(modal.getJugadorDescription());
        fechaTV.setText("Fecha:" + modal.getJugadorFecha());
        Picasso.get().load(modal.getJugadorImg()).placeholder(R.mipmap.img).into(jugadorIV);
        Button viewBtn = layout.findViewById(R.id.idBtnVIewDetails);
        Button editBtn = layout.findViewById(R.id.idBtnEditJugador);
        Button compartirBtn= layout.findViewById(R.id.checkBox);

        //agregando el click listener para nuestro edit button.
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //en la línea de abajo estamos abriendo nuestro EditJugadorActivity en la línea de abajo.
                Intent i = new Intent(MainActivity.this, EditJugadorActivity.class);
                //en la línea de abajo estamos pasando nuestro jugador modal
                i.putExtra("jugador", modal);
                startActivity(i);
            }
        });
        //agregamos el click listener para nuestro button de compartir
        compartirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hh= jugadorNameTV.getText().toString();

                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");

                intentShare.putExtra(Intent.EXTRA_SUBJECT,"My Subject Here ... ");
                intentShare.putExtra(Intent.EXTRA_TEXT,hh);

                startActivity(Intent.createChooser(intentShare, "Shared the textt ..."));

            }
        });
        editBtn.setVisibility(View.VISIBLE);
        //adding click listener para nuestro botón de vista en la línea de abajo.
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //en la línea de abajo estamos navegando al navegador para mostrar los detalles del jugador desde su url
                Intent i = new Intent(Intent.ACTION_VIEW);
                //para acceder a un url
                i.setData(Uri.parse(modal.getJugadorLink()));
                startActivity(i);
            }
        });

    }

//search
    public void filterlist(String strSearch) {
        //RECIBIMOS POR PARAMETRO EL TEXTO DEL BUSCADOR
        ArrayList<com.example.tumejorjugadores.JugadorRVModal> filteredList = new ArrayList<>();
        for (JugadorRVModal j: jugadorRVModalArrayList) {
            if (j.getJugadorName().toLowerCase().contains(strSearch.toLowerCase())) {
                filteredList.add(j);
            }
        }
        //ESTE SE USA PARA BUSCAR CADA VEZ QUE SE ESCRIBE O BORRA UNA LETRA, CUNADO SE MODIFICA EL TEXTO
        if (filteredList.isEmpty()) {
               Toast.makeText(this,"No date fond", Toast.LENGTH_SHORT).show();
            } else { //SI RECIBE TEXTO, LIMPIAMOS LA LISTA, Y VAMOS AÑADIENDO LAS NOTICIAS QUE CONTENGAN ESE TEXTO
            jugadorRVAdapter.setFilteredList(filteredList);

            }


        }



    }


