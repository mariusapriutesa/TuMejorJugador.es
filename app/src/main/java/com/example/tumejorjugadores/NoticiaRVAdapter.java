package com.example.tumejorjugadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticiaRVAdapter extends RecyclerView.Adapter<NoticiaRVAdapter.ViewHolder> {
    public static int JugadorClickInterface;
    private static ArrayList<NoticiaRVModal> noticiaRVModalArrayList;
    //creando variables para nuestra lista, contexto, interfaz y posición.

    private final Context context;
    private final JugadorClickInterface  jugadorClickInterface;
    int lastPos = -1;

    public void setFilteredList(ArrayList<NoticiaRVModal> filteredList){
    noticiaRVModalArrayList =filteredList;
   notifyDataSetChanged();
    }

    //creando los  constructores.
    public NoticiaRVAdapter(ArrayList<NoticiaRVModal> noticiaRVModalArrayList, Context context, JugadorClickInterface jugadorClickInterface)  {
        this.noticiaRVModalArrayList = noticiaRVModalArrayList;

        this.context = context;

        this.jugadorClickInterface = jugadorClickInterface;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflando un archivo de diseño en la línea de abajo.
        View view = LayoutInflater.from(context).inflate(R.layout.jugador_rv_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //configurando datos para nuestro elemento de RecyclerView en la línea de abajo.
        NoticiaRVModal noticiaRVModal = noticiaRVModalArrayList.get(position);
        holder.jugadorTVName.setText(noticiaRVModal.getJugadorName());
        holder.jugadorFechaTV.setText("" + noticiaRVModal.getJugadorFecha());

        Picasso.get().load(noticiaRVModal.getJugadorImg()).placeholder(R.mipmap.img).into(holder.jugadorIV);
        // agregando animación al los elementos de recycleView en la línea de abajo.

        setAnimation(holder.itemView, position);
        Intent i2 =new Intent(String.valueOf(NoticiaRVAdapter.this));
        i2.putExtra("posicion",position);
        holder.jugadorIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugadorClickInterface.onJugadorClick(position);



            }

        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // en la línea de abajo estamos configurando la animación.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return noticiaRVModalArrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //creando variable para nuestra vista de imagen y vista de texto en la línea de abajo.
        private final ImageView jugadorIV;
        private final TextView jugadorTVName, jugadorFechaTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
            jugadorIV = itemView.findViewById(R.id.idIVJugador);
            jugadorTVName = itemView.findViewById(R.id.idTVJUgadorName);
            jugadorFechaTV = itemView.findViewById(R.id.idTVCouseFecha);
        }
    }

    //creando una interfaz para hacer clic
    public interface JugadorClickInterface {
        void onJugadorClick(int position);

    }


}
