package com.example.tumejorjugadores;

import android.os.Parcel;
import android.os.Parcelable;

public class JugadorRVModal implements Parcelable {
    //creando variables para nuestros campos.
    private String jugadorName;
    private String jugadorDescription;
    private String jugadorFecha;
    private String jugadorImg;
    private String jugadorLink;
    private String jugadorId;

    public String getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(String jugadorId) {
        this.jugadorId = jugadorId;
    }




    //creando un constructor vacio.
    public JugadorRVModal() {

    }

    protected JugadorRVModal(Parcel in) {
        jugadorName = in.readString();
        jugadorId = in.readString();
        jugadorDescription = in.readString();
        jugadorFecha = in.readString();
        jugadorImg = in.readString();
        jugadorLink = in.readString();

    }

    public static final Creator<JugadorRVModal> CREATOR = new Creator<JugadorRVModal>() {
        @Override
        public JugadorRVModal createFromParcel(Parcel in) {
            return new JugadorRVModal(in);
        }

        @Override
        public JugadorRVModal[] newArray(int size) {
            return new JugadorRVModal[size];
        }
    };

    //creating getter and setter methods.
    public String getJugadorName() {
        return jugadorName;
    }

    public void setJugadorName(String jugadorName) {
        this.jugadorName = jugadorName;
    }

    public String getJugadorDescription() {
        return jugadorDescription;
    }

    public void setJugadorDescription(String jugadorDescription) {
        this.jugadorDescription = jugadorDescription;
    }

    public String getJugadorFecha() {
        return jugadorFecha;
    }

    public void setJugadorFecha(String jugadorFecha) {
        this.jugadorFecha = jugadorFecha;
    }



    public String getJugadorImg() {
        return jugadorImg;
    }

    public void setJugadorImg(String jugadorImg) {
        this.jugadorImg = jugadorImg;
    }

    public String getJugadorLink() {
        return jugadorLink;
    }

    public void setJugadorLink(String jugadorLink) {
        this.jugadorLink = jugadorLink;
    }


    public JugadorRVModal(String jugadorName, String jugadorDescription, String jugadorFecha, String jugadorImg, String jugadorLink, String jugadorId) {
        this.jugadorName = jugadorName;
        this.jugadorDescription = jugadorDescription;
        this.jugadorFecha = jugadorFecha;
        this.jugadorImg = jugadorImg;
        this.jugadorLink = jugadorLink;
        this.jugadorId = jugadorId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(jugadorName);
        dest.writeString(jugadorId);
        dest.writeString(jugadorDescription);
        dest.writeString(jugadorFecha);
        dest.writeString(jugadorImg);
        dest.writeString(jugadorLink);
    }
}
