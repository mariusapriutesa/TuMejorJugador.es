package com.example.tumejorjugadores.ApiClases;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumejorjugadores.ApiModel.NewsApiResponse;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.OnFetchDataListener;
import com.example.tumejorjugadores.Interfaces.SelectListener;
import com.example.tumejorjugadores.MainActivity;
import com.example.tumejorjugadores.R;
import com.example.tumejorjugadores.ApiModel.NewsApiResponse;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.OnFetchDataListener;
import com.example.tumejorjugadores.Interfaces.SelectListener;

import java.util.List;

public class LatinAmericaNoticiasActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    LatinAmericaAdapter latinAmericaAdapter;
    Button  ArgentinaBtn, VenezuelaBtn, MexicoBtn,  CubaBtn, ColumbiaBtn;
    ProgressDialog progressDialog;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latin_america_noticias);
     //   getSupportActionBar().hide();




        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Buscando noticias...");
        progressDialog.show();


        ArgentinaBtn = findViewById(R.id.btn_2);
        ArgentinaBtn.setOnClickListener(this);
        VenezuelaBtn = findViewById(R.id.btn_3);
        VenezuelaBtn.setOnClickListener(this);
        MexicoBtn = findViewById(R.id.btn_4);
        MexicoBtn.setOnClickListener(this);
        CubaBtn = findViewById(R.id.btn_6);
        CubaBtn.setOnClickListener(this);
        ColumbiaBtn = findViewById(R.id.btn_7);
        ColumbiaBtn.setOnClickListener(this);


        LatinAmericaRequestManager manager = new LatinAmericaRequestManager(LatinAmericaNoticiasActivity.this);
        manager.getNewsHeadlines(listener, "ar", null);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // agregando un clik list para la opción seleccionada en la línea de abajo.
        int id = item.getItemId();
        switch (id) {
            case R.id.España:
                Toast.makeText(getApplicationContext(),"Noticias España",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(LatinAmericaNoticiasActivity.this, MainActivity.class);
                startActivity(i2);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //en la línea de abajo estamos inflando nuestro archivo de menú para mostrar nuestras opciones de menú.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private final OnFetchDataListener<NewsApiResponse> listener =new OnFetchDataListener<NewsApiResponse>() {

        @Override
        public void onFetchData(List<NewsHeadline> data,  String message) {
            showNews(data);
            progressDialog.dismiss();
            if (data.isEmpty()){
                Toast.makeText(LatinAmericaNoticiasActivity.this, "No se ha encontrado las noticias", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(LatinAmericaNoticiasActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadline> headlines) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        latinAmericaAdapter = new LatinAmericaAdapter(this, headlines, this);
        recyclerView.setAdapter(latinAmericaAdapter);

    }

    @Override
    public void OnNewsClicked(NewsHeadline headline) {
        startActivity(new Intent(LatinAmericaNoticiasActivity.this, LatinAmericaNoticiasDetailsActivity.class)
        .putExtra("data", headline));

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();



        progressDialog.setTitle("Cogiendo noticias de " + category);
        progressDialog.show();
        LatinAmericaRequestManager manager = new LatinAmericaRequestManager(LatinAmericaNoticiasActivity.this);
        manager.getNewsHeadlines(listener, category, null);
    }
}