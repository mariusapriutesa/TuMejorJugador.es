package com.example.tumejorjugadores.ApiClases;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumejorjugadores.ApiModel.NewsApiResponse;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.OnFetchDataListener;
import com.example.tumejorjugadores.Interfaces.SelectListener;
import com.example.tumejorjugadores.R;
import com.example.tumejorjugadores.ApiModel.NewsApiResponse;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.OnFetchDataListener;
import com.example.tumejorjugadores.Interfaces.SelectListener;

import java.util.List;

public class LatinAmericaNoticiasActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    RecyclerView recyclerView;
    LatinAmericaAdapter latinAmericaAdapter;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    ProgressDialog progressDialog;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latin_america_noticias);





        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching News...");
        progressDialog.show();


        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(this);


        LatinAmericaRequestManager manager = new LatinAmericaRequestManager(LatinAmericaNoticiasActivity.this);
        manager.getNewsHeadlines(listener, "ar", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener =new OnFetchDataListener<NewsApiResponse>() {

        @Override
        public void onFetchData(List<NewsHeadline> data,  String message) {
            showNews(data);
            progressDialog.dismiss();
            if (data.isEmpty()){
                Toast.makeText(LatinAmericaNoticiasActivity.this, "No Data Found!", Toast.LENGTH_LONG).show();
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
        progressDialog.setTitle("Fetching News Of " + category);
        progressDialog.show();
        LatinAmericaRequestManager manager = new LatinAmericaRequestManager(LatinAmericaNoticiasActivity.this);
        manager.getNewsHeadlines(listener, category, null);
    }
}