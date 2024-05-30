package com.example.frontend;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BudgetReformKitchenActivity extends BaseActivity {

    EditText etKitchenHeight;
    EditText etKitchenWidth;
    EditText etKitchenLength;
    EditText etKitchenTiles;
    EditText etKitchenSurfaceFurniture;
    EditText etKitchenSurfaceWorktop;
    RadioGroup rgKitchenReno;
    RadioButton rbKitchenReno;
    final Integer kitchenRenoFactor = 1000;
    Integer selectedFactor;
    Float height;
    Float width;
    Float length;
    Float tiles;
    Integer surfaceFurniture;
    Integer surfaceWorktop;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etKitchenHeight=findViewById(R.id.etBudgetReformKitchenHeight);
        etKitchenWidth=findViewById(R.id.etBudgetReformKitchenWidth);
        etKitchenLength=findViewById(R.id.etBudgetReformKitchenLength);
        etKitchenTiles=findViewById(R.id.etBudgetReformKitchenTiles);
        etKitchenSurfaceFurniture=findViewById(R.id.etBudgetReformKitchenFurnitureM);
        etKitchenSurfaceWorktop=findViewById(R.id.etBudgetReformKitchenWorktopM);
        rgKitchenReno=findViewById(R.id.rgReformKitchenReno);
    }

    @Override
    protected int getLayoutResource() { return R.layout.activity_budget_reform_kitchen;}

    private boolean validateFormData(){
        height = Float.parseFloat(etKitchenHeight.getText().toString());
        width = Float.parseFloat(etKitchenWidth.getText().toString());
        length = Float.parseFloat(etKitchenLength.getText().toString());
        tiles = Float.parseFloat(etKitchenTiles.getText().toString());
        rbKitchenReno=findViewById(rgKitchenReno.getCheckedRadioButtonId());
        String selectedReno=rbKitchenReno.getText().toString();
        selectedFactor = selectedReno.equals("Yes")?1000:0;
        return false;
    }
}