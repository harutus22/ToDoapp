package com.example.apple.todoapp.fragments;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.todoapp.R;
import com.example.apple.todoapp.activity.MainActivity;
import com.example.apple.todoapp.adapters.CardViewAdapter;
import com.example.apple.todoapp.viewType.Info;

public class MainFragment extends Fragment {

    private CardViewAdapter.OnItemSelectedListener OnItemSelectedListener = new CardViewAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Info info) {
            if(status) {
                edit(info);
            }
        }
    };

    public static final String ARG_PARAM = "param1";
    public static final String ARG_STRING = "string";
    @SuppressLint("StaticFieldLeak")
    public static MainFragment fragment = new MainFragment();
    public RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter = new CardViewAdapter();
    @SuppressLint("StaticFieldLeak")
    public static FloatingActionButton addBtn;
    public static boolean status = false;
    public static MenuItem item;

    public MainFragment() {
    }

    public static MainFragment newInstance(Info info, String s) {
        Bundle args = new Bundle();
        args.putString(ARG_STRING, s);
        args.putParcelable(ARG_PARAM, info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_to_do_main, menu);
        item = menu.findItem(R.id.menu_edit);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                if(item.getTitle().equals("edit")) {
                    item.setIcon(R.drawable.ic_accept);
                    item.setTitle("accept");
                    addBtn.setVisibility(View.INVISIBLE);
                    status = true;
                }
                else if(item.getTitle().equals("accept")){
                    item.setIcon(R.drawable.ic_pencil);
                    item.setTitle("edit");
                    addBtn.setVisibility(View.VISIBLE);
                    status = false;
                }
                return true;
            case R.id.delete:
                if(item.getTitle().equals("Delete")){
                    item.setTitle("Choosing");
                }

            default:
                item.setIcon(R.drawable.ic_pencil);
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        cardViewAdapter.setOnItemSelectedListener(OnItemSelectedListener);
        recyclerView = view.findViewById(R.id.toDoInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cardViewAdapter);
        recyclerView.setEnabled(false);
        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add();
                }
            });
        if (getArguments() != null) {
            if(!status) {
                addBtn.setVisibility(View.VISIBLE);
            }
            else if(status) {
                addBtn.setVisibility(View.INVISIBLE);
            }
            Info info = getArguments().getParcelable(ARG_PARAM);
            String s = getArguments().getString(ARG_STRING);
            switch (s){
                case "addBtn": cardViewAdapter.addData(info);
                break;
                case "edit": cardViewAdapter.updateData(info);
                break;
            }
        }
    }

    public void add(){
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.placeHolder, new FragmentView());
        fragmentManager.commit();
    }

    public void edit(Info info){
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        FragmentView fragmentView = FragmentView.newInstance(info);
        fragmentManager.replace(R.id.placeHolder, fragmentView);
        fragmentManager.commit();
    }

}
