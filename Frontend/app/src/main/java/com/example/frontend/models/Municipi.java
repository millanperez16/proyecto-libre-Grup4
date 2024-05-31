package com.example.frontend.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Municipi {
    @SerializedName("elements")
    private ArrayList<Element> elements;
    public ArrayList<Element> getElements() {
        return elements;
    }
}
