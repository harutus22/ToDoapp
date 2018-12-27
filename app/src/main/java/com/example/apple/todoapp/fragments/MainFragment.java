package com.example.apple.todoapp.fragments;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.todoapp.R;
import com.example.apple.todoapp.adapters.CardViewAdapter;
import com.example.apple.todoapp.viewType.Info;

public class MainFragment extends Fragment {

    private CardViewAdapter.OnItemSelectedListener OnItemSelectedListener = new CardViewAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Info info) {
            edit(info);
        }
    };

    public static final String ARG_PARAM = "param1";
    public static final String ARG_STRING = "string";
    private static MainFragment fragment = new MainFragment();
    public static FragmentView fragmentView = new FragmentView();
    CardViewAdapter cardViewAdapter = new CardViewAdapter();

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cardViewAdapter.setOnItemSelectedListener(OnItemSelectedListener);
        RecyclerView recyclerView = view.findViewById(R.id.toDoInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cardViewAdapter);
        FloatingActionButton add = view.findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add();
                }
            });
        if (getArguments() != null) {
            Info info = getArguments().getParcelable(ARG_PARAM);
            String s = getArguments().getString(ARG_STRING);
            switch (s){
                case "add": cardViewAdapter.addData(info);
                break;
                case "edit": cardViewAdapter.updateData(info);
                break;
            }
        }
    }

    public void add(){
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        fragmentManager.replace(R.id.placeHolder, new FragmentView());
        fragmentManager.addToBackStack(null);
        fragmentManager.commit();
//        Intent intent = new Intent(this, CreatEdit.class);
//        startActivityForResult(intent, REQUEST_ADDITEM);
    }

    public void edit(Info info){
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        fragmentView = FragmentView.newInstance(info);
        fragmentManager.replace(R.id.placeHolder, fragmentView);
        fragmentManager.commit();

//        Intent edit = new Intent(this, CreatEdit.class);
//        edit.putExtra(CreatEdit.KEY ,info);
//        startActivityForResult(edit, REQUEST_EDIT);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//            case REQUEST_ADDITEM:
//                if(resultCode == RESULT_OK) {
//                    Info info = data.getParcelableExtra(CreatEdit.KEY);
//                    cardViewAdapter.addData(info);
//                }
//                break;
//                case REQUEST_EDIT:
//                    if(resultCode == RESULT_OK){
//                        Info info = data.getParcelableExtra((CreatEdit.KEY));
//                        cardViewAdapter.updateData(info);
//                    }
//                    break;
//            }
//            super.onActivityResult(requestCode, resultCode, data);
//        }
}
