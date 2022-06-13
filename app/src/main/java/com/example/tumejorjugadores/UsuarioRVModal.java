package com.example.tumejorjugadores;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UsuarioRVModal implements Parcelable {
   //creating variables for our different fields.
    private String userNameEdt;
    private String passwordEdt;
    private String rols;
    private String usuarioId;
    private String usuarioImg;
    private ArrayList<JugadorRVModal> Noticias;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ArrayList<JugadorRVModal> getNoticias() {
        return Noticias;
    }

    public void setNoticias(ArrayList<JugadorRVModal> noticias) {
        Noticias = noticias;
    }

    //creando un constructor vacio.
    public UsuarioRVModal() {

    }

    protected UsuarioRVModal(Parcel in) {
        userNameEdt = in.readString();
        usuarioId = in.readString();
        passwordEdt = in.readString();
        rols = in.readString();
        usuarioImg = in.readString();
     //  Noticias=in.readArrayList();
    }

    public static final Creator<UsuarioRVModal> CREATOR = new Creator<UsuarioRVModal>() {
        @Override
        public UsuarioRVModal createFromParcel(Parcel in) {
            return new UsuarioRVModal(in);
        }

        @Override
        public UsuarioRVModal[] newArray(int size) {
            return new UsuarioRVModal[size];
        }
    };

    //creating getter and setter methods.
    public String getUserNameEdt() {
        return userNameEdt;
    }

    public void setUserNameEdt(String userNameEdt) {
        this.userNameEdt = userNameEdt;
    }

    public String getPasswordEdt() {
        return passwordEdt;
    }

    public void setPasswordEdt(String passwordEdt) {
        this.passwordEdt = passwordEdt;
    }

    public String getRols() {
        return rols;
    }

    public void setRols(String rols) {
        this.rols = rols;
    }



    public String getUsuarioImg() {
        return usuarioImg;
    }

    public void setUsuarioImg(String usuarioImg) {
        this.usuarioImg = usuarioImg;
    }




    public UsuarioRVModal(String usuarioId, String userNameEdt, String passwordEdt, String rols,  String jugadorImg, ArrayList<JugadorRVModal> Noticias) {
        this.userNameEdt = userNameEdt;
        this.usuarioId = usuarioId;
        this.passwordEdt = passwordEdt;
        this.rols = rols;
        this.usuarioImg = jugadorImg;
        this.Noticias=Noticias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userNameEdt);
        dest.writeString(usuarioId);
        dest.writeString(passwordEdt);
        dest.writeString(rols);
        dest.writeString(usuarioImg);
        dest.writeList( Noticias);
    }
}
