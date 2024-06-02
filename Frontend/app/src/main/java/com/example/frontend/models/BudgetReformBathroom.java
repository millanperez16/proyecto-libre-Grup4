package com.example.frontend.models;

public class BudgetReformBathroom extends Budget{
    private Float alto;
    private Float ancho;
    private Float largo;
    private Float precioAzulejoM2;
    private Integer modifInstalacion;
    private Integer numSanitarios;

    public BudgetReformBathroom(String destinatario, String direccion, String codigoPostal, String municipio, String provincia, Float alto, Float ancho, Float largo, Float precioAzulejoM2, Integer modifInstalacion, Integer numSanitarios) {
        super(destinatario, direccion, codigoPostal, municipio, provincia);
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.precioAzulejoM2 = precioAzulejoM2;
        this.modifInstalacion = modifInstalacion;
        this.numSanitarios = numSanitarios;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Float getPrecioAzulejoM2() {
        return precioAzulejoM2;
    }

    public void setPrecioAzulejoM2(Float precioAzulejoM2) {
        this.precioAzulejoM2 = precioAzulejoM2;
    }

    public Integer getModifInstalacion() {
        return modifInstalacion;
    }

    public void setModifInstalacion(Integer modifInstalacion) {
        this.modifInstalacion = modifInstalacion;
    }

    public Integer getNumSanitarios() {
        return numSanitarios;
    }

    public void setNumSanitarios(Integer numSanitarios) {
        this.numSanitarios = numSanitarios;
    }
}
