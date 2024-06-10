package com.example.frontend.models;

public class BudgetNewBuild extends Budget {
    private Integer superficie;
    private Float calidad;

    public BudgetNewBuild(String nameSurnames, String street, String postalCode, String municipality, String province, Integer superficie, Float calidad) {
        super(nameSurnames, street, postalCode, municipality, province);
        this.superficie = superficie;
        this.calidad = calidad;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Float getCalidad() {
        return calidad;
    }

    public void setCalidad(Float calidad) {
        this.calidad = calidad;
    }

    @Override
    public String toString() {
        String budgetData = super.toString();
        return budgetData +
                superficie + System.lineSeparator() +
                calidad;
    }
}
