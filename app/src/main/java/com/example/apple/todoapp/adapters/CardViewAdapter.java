package com.example.apple.todoapp.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.todoapp.adapters.cardViewHolder.ToDoHolder;
import com.example.apple.todoapp.viewType.Info;
import com.example.apple.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<ToDoHolder> {
    private List<Info> data = new ArrayList<>();
    private OnItemSelectedListener onItemSelected;
    private int index = -1;

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewlayout,
                viewGroup,false);
        return new ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder toDoHolder, int i) {
        Info info = data.get(i);
        toDoHolder.getTitle().setText(info.getTitle());
        toDoHolder.getDescription().setText(info.getDescription());
        toDoHolder.getDate().setText(info.getDate());
        switch (info.getPriority()){
            case "Low": toDoHolder.getPriority().setBackgroundColor(Color.BLUE);
                break;
            case "Minor": toDoHolder.getPriority().setBackgroundColor(Color.CYAN);
                break;
            case "Normal": toDoHolder.getPriority().setBackgroundColor(Color.GREEN);
                break;
            case "Major": toDoHolder.getPriority().setBackgroundColor(Color.YELLOW);
                break;
            case "High": toDoHolder.getPriority().setBackgroundColor(Color.RED);
                break;
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        onItemSelected = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Info info);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addData(Info item){
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

}
