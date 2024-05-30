package com.example.frontend.models;

public class BudgetReformBathroom extends Budget{
    private Float height;
    private Float width;
    private Float length;
    private Float tiles;
    private Integer piecesAdd;
    private Integer piecesRemove;

    public BudgetReformBathroom(User user, Float height, Float width, Float length, Float tiles, Integer piecesAdd, Integer piecesRemove) {
        super(user);
        this.height = height;
        this.width = width;
        this.length = length;
        this.tiles = tiles;
        this.piecesAdd = piecesAdd;
        this.piecesRemove = piecesRemove;
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

    public Integer getPiecesAdd() {
        return piecesAdd;
    }

    public void setPiecesAdd(Integer piecesAdd) {
        this.piecesAdd = piecesAdd;
    }

    public Integer getPiecesRemove() {
        return piecesRemove;
    }

    public void setPiecesRemove(Integer piecesRemove) {
        this.piecesRemove = piecesRemove;
    }
}
