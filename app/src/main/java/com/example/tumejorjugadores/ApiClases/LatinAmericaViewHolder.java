package com.example.tumejorjugadores.ApiClases;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumejorjugadores.R;

public class LatinAmericaViewHolder extends RecyclerView.ViewHolder {

    public TextView text_title, text_autor;
    public CardView cardView;
    public ImageView img_headline;

    public LatinAmericaViewHolder(@NonNull View itemView) {
        super(itemView);
        text_autor = itemView.findViewById(R.id.text_autor);
        img_headline = itemView.findViewById(R.id.img_headline);
        text_title = itemView.findViewById(R.id.text_title);
        cardView = itemView.findViewById(R.id.main_container);
    }
}
