package com.example.frontend.models;

public abstract class Budget {
    private String destinatario;
    private String direccion;
    private String codigoPostal;
    private String municipio;
    private String provincia;
    private String referencia;

    public Budget(String destinatario, String direccion, String codigoPostal, String municipio, String provincia) {
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
        this.provincia = provincia;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return  "destinatario='" + destinatario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", municipio='" + municipio + '\'' +
                ", provincia='" + provincia + '\'' +
                ", referencia='" + referencia + '\''; }
}
