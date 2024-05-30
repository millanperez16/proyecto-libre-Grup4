package com.example.frontend.models;

public class User {
    private String nombre;
    private String correo;
    private String tlf;
    private String passwd;

    public User(String correo, String passwd) {
        this.correo = correo;
        this.passwd = passwd;
    }

    public User(String nombre, String correo, String tlf, String passwd) {
        this.nombre = nombre;
        this.correo = correo;
        this.tlf = tlf;
        this.passwd = passwd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
