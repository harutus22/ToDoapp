package com.example.apple.todoapp.adapters.cardViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.apple.todoapp.R;

public class ToDoHolder extends RecyclerView.ViewHolder {
    private TextView title, description, date;
    private View priority;

    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getDate() {
        return date;
    }

    public View getPriority() {
        return priority;
    }

    public ToDoHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.titleCardView);
        description = itemView.findViewById(R.id.descriptionCardView);
        date = itemView.findViewById(R.id.dateCardView);
        priority = itemView.findViewById(R.id.priorityCardView);
    }
}
