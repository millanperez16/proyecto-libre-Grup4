package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;

import com.example.frontend.models.Budget;
import com.example.frontend.models.BudgetReformBathroom;
import com.example.frontend.models.User;

public class BudgetReformBathroomActivity extends BaseActivity{

    EditText etBathroomHeight;
    EditText etBathroomWidth;
    EditText etBathroomLength;
    EditText etBathroomTiles;
    RadioGroup rgPiecesAdd;
    RadioGroup rgPiecesRemove;
    Float height;
    Float width;
    Float length;
    Float tiles;
    RadioButton btnAdd;
    Integer piecesAdd;
    RadioButton btnRemove;
    Integer piecesRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnReformBathroomSubmit = findViewById(R.id.btnReformBathroomSubmit);
        btnReformBathroomSubmit.setOnClickListener(v -> {
            if(!validateFormData()){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }else{
                Bundle bundle=getIntent().getExtras();
                User user = new User(bundle.getString("nameSurname"),bundle.getString("street"),bundle.getString("postalCode"),bundle.getString("municipality"));
                Budget bathroomBudget=new BudgetReformBathroom(user,height,width,length,tiles,piecesAdd,piecesRemove);
                
            }
        });

    }

    @Override
    protected int getLayoutResource() { return R.layout.activity_budget_reform_bathroom; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private boolean validateFormData(){
        height=Float.parseFloat(etBathroomHeight.getText().toString());
        width=Float.parseFloat(etBathroomWidth.getText().toString());
        length=Float.parseFloat(etBathroomLength.getText().toString());
        tiles=Float.parseFloat(etBathroomTiles.getText().toString());
        btnAdd = findViewById(rgPiecesAdd.getCheckedRadioButtonId());
        piecesAdd= Integer.parseInt(btnAdd.getText().toString());
        btnRemove = findViewById(rgPiecesRemove.getCheckedRadioButtonId());
        piecesRemove= Integer.parseInt(btnRemove.getText().toString());
        return !height.isNaN() && !width.isNaN() && !length.isNaN() && !tiles.isNaN() && piecesAdd!=null && piecesRemove!=null;
    }
}
