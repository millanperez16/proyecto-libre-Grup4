package com.example.frontend.models;

public class BudgetReformKitchen extends Budget{
    private Float height;
    private Float width;
    private Float length;
    private Float tiles;
    private Integer selectedFactor;
    private Integer surfaceFurniture;
    private Integer surfaceWorktop;

    public BudgetReformKitchen(User user, Float height, Float width, Float length, Float tiles, Integer selectedFactor, Integer surfaceFurniture, Integer surfaceWorktop) {
        super(user);
        this.height = height;
        this.width = width;
        this.length = length;
        this.tiles = tiles;
        this.selectedFactor = selectedFactor;
        this.surfaceFurniture = surfaceFurniture;
        this.surfaceWorktop = surfaceWorktop;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getTiles() {
        return tiles;
    }

    public void setTiles(Float tiles) {
        this.tiles = tiles;
    }

    public Integer getSelectedFactor() {
        return selectedFactor;
    }

    public void setSelectedFactor(Integer selectedFactor) {
        this.selectedFactor = selectedFactor;
    }

    public Integer getSurfaceFurniture() {
        return surfaceFurniture;
    }

    public void setSurfaceFurniture(Integer surfaceFurniture) {
        this.surfaceFurniture = surfaceFurniture;
    }

    public Integer getSurfaceWorktop() {
        return surfaceWorktop;
    }

    public void setSurfaceWorktop(Integer surfaceWorktop) {
        this.surfaceWorktop = surfaceWorktop;
    }
}
