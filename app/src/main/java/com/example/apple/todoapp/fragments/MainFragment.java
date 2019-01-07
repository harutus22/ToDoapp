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
import com.example.apple.todoapp.Tools.SortingTool;
import com.example.apple.todoapp.adapters.CardViewAdapter;
import com.example.apple.todoapp.viewType.Info;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private CardViewAdapter.OnItemSelectedListener OnItemSelectedListener = new CardViewAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Info info) {
            if(status) {
                edit(info);
            }
        }
    };

    private CardViewAdapter.OnItemRemoveSelectedListener OnItemRemoveSelectedListener = new CardViewAdapter.OnItemRemoveSelectedListener() {
        @Override
        public void onItemRemoveSelected(int position) {
            cardViewAdapter.removeData(position);
        }
    };

    public static final String ARG_PARAM = "param1";
    public static final String ARG_STRING = "string";
    public static final String BY_TITLE = "byTitle";
    public static final String BY_DATE = "byDate";
    public static final String BY_PRIORITY = "byPriority";
    public static final String MENU_TITLE_EDIT = "edit";
    public static final String MENU_TITLE_ACCEPT = "accept";
    @SuppressLint("StaticFieldLeak")
    public static MainFragment fragment = new MainFragment();
    public RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter = new CardViewAdapter();
    private FloatingActionButton addBtn;
    private boolean status = false;
    public MenuItem item;
    private List<Info> sortedList = new ArrayList<>();

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
        if(status) {
            item.setIcon(R.drawable.ic_accept);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                if(status && item.getTitle().equals(MENU_TITLE_EDIT)){
                    item.setIcon(R.drawable.ic_pencil);
                    status = false;
                    addBtn.show();
                }
                else if(item.getTitle().equals(MENU_TITLE_EDIT)) {
                    item.setIcon(R.drawable.ic_accept);
                    item.setTitle(MENU_TITLE_ACCEPT);
                    addBtn.hide();
                    status = true;
                }
                else if(item.getTitle().equals(MENU_TITLE_ACCEPT)){
                    item.setIcon(R.drawable.ic_pencil);
                    item.setTitle(MENU_TITLE_EDIT);
                    addBtn.show();
                    status = false;
                }

                break;
            case R.id.delete:
                if(item.getTitle().equals("delete")){
                    item.setIcon(R.drawable.ic_accept);
                    break;

                }
                else if(item.getTitle().equals("accept")){

                }
                break;
            case R.id.sort_Date:
                sortedList = cardViewAdapter.getData();
                sortedList = SortingTool.sort(sortedList, BY_DATE);
                notifyAdapter();
                break;
            case R.id.sort_Title:
                sortedList = cardViewAdapter.getData();
                sortedList = SortingTool.sort(sortedList, BY_TITLE);
                notifyAdapter();
                break;
            case R.id.sort_Priority:
                sortedList = cardViewAdapter.getData();
                sortedList = SortingTool.sort(sortedList, BY_PRIORITY);
                notifyAdapter();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        cardViewAdapter.setOnItemSelectedListener(OnItemSelectedListener);
        cardViewAdapter.setOnItemRemoveSelectedListener(OnItemRemoveSelectedListener);
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
        if(getArguments() != null) {
            if(!status) {
                addBtn.show();
            }
            else{
                addBtn.hide();
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
        addBtn.hide();
    }

    public void edit(Info info){
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        FragmentView fragmentView = FragmentView.newInstance(info);
        fragmentManager.replace(R.id.placeHolder, fragmentView);
        fragmentManager.commit();
    }

    public void notifyAdapter(){
        cardViewAdapter.setData(sortedList);
        cardViewAdapter.notifyDataSetChanged();
    }
}
