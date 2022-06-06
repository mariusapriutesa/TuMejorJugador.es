package com.example.tumejorjugadores;

import android.os.Parcel;
import android.os.Parcelable;

public class UsuarioRVModal {
   /* //creating variables for our different fields.
    private String userNameEdt;
    private String passwordEdt;
    private String rols;
    private String usuarioId;



    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = this.usuarioId;
    }


    //creando un constructor vacio.
    public UsuarioRVModal() {

    }

    protected UsuarioRVModal(Parcel in) {
        userNameEdt = in.readString();
        usuarioId = in.readString();
        passwordEdt = in.readString();
        rols = in.readString();
    }

    public static final Parcelable.Creator<UsuarioRVModal> CREATOR = new Parcelable.Creator<UsuarioRVModal>() {
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
    public String getUserName() {
        return userNameEdt;
    }

    public void setUserNameEdt(String userNameEdt) {
        this.userNameEdt = userNameEdt;
    }

    public String getJugadorDescription() {
        return passwordEdt;
    }

    public void setPasswordEdt(String passwordEdt) {
        this.passwordEdt = passwordEdt;
    }





    public String getUserRols() {
        return rols;
    }

    public void setUserRols(String rols) {
        this.rols = rols;
    }




    public UsuarioRVModal(String userId, String userNameEdt, String passwordEdt,  String rols) {
        this.userNameEdt = userNameEdt;
        this.usuarioId = userId;
        this.passwordEdt = passwordEdt;
        this.rols = rols;
    }

   // @Override
    public int describeContents() {
        return 0;
    }

   // @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userNameEdt);
        dest.writeString(usuarioId);
        dest.writeString(passwordEdt);
        dest.writeString(rols);
    }



*/


}
