package com.example.tumejorjugadores.ApiClases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.SelectListener;
import com.example.tumejorjugadores.R;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.Interfaces.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LatinAmericaAdapter extends RecyclerView.Adapter<LatinAmericaViewHolder> {
    private Context context;
    private List<NewsHeadline> headlineList;
    private SelectListener listener;

    public LatinAmericaAdapter(Context context, List<NewsHeadline> headlineList, SelectListener listener) {
        this.context = context;
        this.headlineList = headlineList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LatinAmericaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LatinAmericaViewHolder(LayoutInflater.from(context).inflate(R.layout.latin_america_recycleview_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LatinAmericaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_title.setText(headlineList.get(position).getTitle());
        if (headlineList.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlineList.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.text_autor.setText(headlineList.get(position).getSource().getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(headlineList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlineList.size();
    }
}
