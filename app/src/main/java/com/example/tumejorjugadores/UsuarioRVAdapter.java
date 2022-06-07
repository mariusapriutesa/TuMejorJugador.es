package com.example.tumejorjugadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class UsuarioRVAdapter {
    private static ArrayList<UsuarioRVModal> usuarioRVModalArrayList;
    //creando variables para nuestra lista, contexto, interfaz y posición.

    private Context context;
    private UsuarioClickInterface usuarioClickInterface;
    MainActivity adapter;
    int lastPos = -1;
    //creando los  constructores.
    public UsuarioRVAdapter(ArrayList<UsuarioRVModal> usuarioRVModalArrayList, Context context)  {
        this.usuarioRVModalArrayList = usuarioRVModalArrayList;

        this.context = context;

        this.usuarioClickInterface = usuarioClickInterface;

    }

    public UsuarioRVAdapter(String usuarioId, String userName, String usuarioImg, TextInputEditText passwordEdt, String rolEdt) {
    }

    public int getItemCount() {
        return usuarioRVModalArrayList.size();
    }
    ///***********************

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //creando variable para nuestra vista de imagen y vista de texto en la línea de abajo.
        private ImageView jugadorIV;
        private TextView jugadorTV, jugadorFechaTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
        }
    }
    //creando una interfaz para hacer clic
    public interface UsuarioClickInterface {
        void onUsuarioClick(int position);
    }

}
