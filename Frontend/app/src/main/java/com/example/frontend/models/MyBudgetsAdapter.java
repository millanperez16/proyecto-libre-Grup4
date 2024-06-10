package com.example.frontend.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.frontend.R;

import java.util.List;

public class MyBudgetsAdapter extends RecyclerView.Adapter<MyBudgetsAdapter.CustomViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Budget budget);
    }

    private List<Budget> budgets;
    private OnItemClickListener onItemClickListener;

    public MyBudgetsAdapter(List<Budget> budgets, OnItemClickListener onItemClickListener) {
        this.budgets = budgets;
        this.onItemClickListener = onItemClickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View myView;
        TextView tvBudget;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            tvBudget = myView.findViewById(R.id.budget);
        }

        public void bind(final Budget budget, final OnItemClickListener listener) {
            tvBudget.setText(budget.getReferencia());
            itemView.setOnClickListener(v -> listener.onItemClick(budget));
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
        holder.bind(budgets.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return budgets != null ? budgets.size() : 0;
    }
}


