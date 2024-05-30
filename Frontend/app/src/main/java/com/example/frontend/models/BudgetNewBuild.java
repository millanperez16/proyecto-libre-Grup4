package com.example.frontend.models;

public class BudgetNewBuild extends Budget{
    private Integer surfaceM2;
    private Float materialsQuality;

    public BudgetNewBuild(User user, Integer surfaceM2, Float materialsQuality) {
        super(user);
        this.surfaceM2 = surfaceM2;
        this.materialsQuality = materialsQuality;
    }

    public Integer getSurfaceM2() {
        return surfaceM2;
    }

    public void setSurfaceM2(Integer surfaceM2) {
        this.surfaceM2 = surfaceM2;
    }

    public Float getMaterialsQuality() {
        return materialsQuality;
    }

    public void setMaterialsQuality(Float materialsQuality) {
        this.materialsQuality = materialsQuality;
    }
}
