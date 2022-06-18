package com.example.tumejorjugadores.ApiClases;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.R;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LatinAmericaNoticiasDetailsActivity extends AppCompatActivity {
    NewsHeadline headline;
    TextView text_title, text_published, text_source, text_data;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latin_america_noticias_detalles);
        headline = (NewsHeadline) getIntent().getSerializableExtra("data");

        text_data = findViewById(R.id.noticia_desc_full);
        text_published = findViewById(R.id.noticia_desc_published);
        text_source = findViewById(R.id.noticia_desc_source);
        text_title = findViewById(R.id.noticia_desc_title);
        img_news = findViewById(R.id.img_news);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(headline.getPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String dateStr = formatter.format(date);

        text_title.setText(headline.getTitle());
        text_source.setText(headline.getAutor());
        text_published.setText(dateStr);
        text_data.setText(headline.getContent());


        Picasso.get().load(headline.getUrlToImage()).into(img_news);



    }
}