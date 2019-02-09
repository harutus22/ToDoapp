package com.example.apple.todoapp.adapters;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.todoapp.adapters.cardViewHolder.ToDoHolder;
import com.example.apple.todoapp.fragments.MainFragment;
import com.example.apple.todoapp.viewType.Info;
import com.example.apple.todoapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardViewAdapter extends RecyclerView.Adapter<ToDoHolder> {
    private List<Info> data = new ArrayList<>();
    private OnItemSelectedListener onItemSelected;
    private OnItemRemoveSelectedListener onItemRemoveSelectedListener;
    private boolean removeVisibility;
    private final String ADD = "add";
    private final String UPDATE = "update";
    private final String REMOVE = "remove";

    private ToDoHolder.OnItemClickListener OnItemClickListener =
            new ToDoHolder.OnItemClickListener() {
                @Override
                public void onItemClick(int adapterPosition) {
                    if (onItemSelected != null) {
                        onItemSelected.onItemSelected(data.get(adapterPosition));
                    }
                }
            };

    private ToDoHolder.OnItemRemoveClickListener OnItemRemoveClickListener = new ToDoHolder.OnItemRemoveClickListener() {
        @Override
        public void onItemClick(int adapterPosition) {
            if(onItemRemoveSelectedListener != null){
                onItemRemoveSelectedListener.onItemRemoveSelected(data.get(adapterPosition));
            }
        }
    };


    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ToDoHolder toDoHolder = new ToDoHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardviewlayout, viewGroup, false));
        toDoHolder.setOnItemClickListener(OnItemClickListener);
        toDoHolder.setOnItemRemoveClickListener(OnItemRemoveClickListener);
        return toDoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder toDoHolder, int i) {
        Info info = data.get(i);
        toDoHolder.getTitle().setText(info.getTitle());
        toDoHolder.getDescription().setText(info.getDescription());
        toDoHolder.getDate().setText(info.getDate());
        switch (info.getPriority()){
            case "e": toDoHolder.getPriority().setBackgroundColor(Color.BLUE);
                break;
            case "d": toDoHolder.getPriority().setBackgroundColor(Color.CYAN);
                break;
            case "c": toDoHolder.getPriority().setBackgroundColor(Color.GREEN);
                break;
            case "b": toDoHolder.getPriority().setBackgroundColor(Color.YELLOW);
                break;
            case "a": toDoHolder.getPriority().setBackgroundColor(Color.RED);
                break;
        }
        if(removeVisibility){
            toDoHolder.getDelete().setVisibility(View.VISIBLE);
        }
        else{
            toDoHolder.getDelete().setVisibility(View.INVISIBLE);
        }
    }

    public void setRemoveVisibility(boolean removeVisibility) {
        this.removeVisibility = removeVisibility;
        notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        onItemSelected = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Info info);
    }

    public interface OnItemRemoveSelectedListener{
        void onItemRemoveSelected(Info info);
    }

    public void setOnItemRemoveSelectedListener(OnItemRemoveSelectedListener onItemRemoveSelectedListener){
        this.onItemRemoveSelectedListener = onItemRemoveSelectedListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Info> getData() {
        return data;
    }

    public void setData(List<Info> data) {
        this.data = data;
    }

    public void addData(Info item){
        data.add(item);
        notifyItemInserted(data.size() - 1);
//        MainFragment.dbManager.createToDo(item);
        database(ADD, item);


    }

    public void removeData(Info info){
        data.remove(info);
        notifyDataSetChanged();
//        MainFragment.dbManager.remove(info.getId());
        database(REMOVE, info);

    }

    public void updateData(Info item){
        for(int i = 0; i < data.size(); ++i) {
            if (Objects.equals(item.getId(), data.get(i).getId())){
                data.set(i, item);
                notifyItemChanged(i);
                break;
            }
//            MainFragment.dbManager.update(item);
            database(UPDATE, item);
        }
    }

    private void database(final String word, final Info item){
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (word){
                    case ADD: MainFragment.toDoDao.insert(item);
                    break;
                    case REMOVE: MainFragment.toDoDao.delete(item);
                    break;
                    case UPDATE: MainFragment.toDoDao.update(item);
                    break;
                }
            }
        }).start();
    }
}
