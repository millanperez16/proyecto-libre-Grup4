package com.example.frontend.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class MyBudgetsAdapter extends RecyclerView.Adapter<MyBudgetsAdapter.CustomViewHolder>{

    private List<Budget> budgets;

    public MyBudgetsAdapter(List<Budget> budgets) {
        this.budgets = budgets;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View myView;
        TextView tvBudget;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            tvBudget = myView.findViewById(R.id.budget);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.tvBudget.setText(budgets.get(position).getReferencia());
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }
}


