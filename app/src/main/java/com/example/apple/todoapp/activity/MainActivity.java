package com.example.apple.todoapp.activity;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.apple.todoapp.R;
import com.example.apple.todoapp.adapters.CardViewAdapter;
import com.example.apple.todoapp.fragments.FragmentView;
import com.example.apple.todoapp.fragments.MainFragment;
import com.example.apple.todoapp.viewType.Info;

public class MainActivity extends AppCompatActivity {
//    private CardViewAdapter cardViewAdapter;
//    private final int REQUEST_ADDITEM = 8;
//    private final int REQUEST_EDIT = 6;
//
//    private CardViewAdapter.OnItemSelectedListener OnItemSelectedListener = new CardViewAdapter.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(Info info) {
//            edit(info);
//        }
//    };

    public static boolean status = false;
    public static MenuItem item;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        cardViewAdapter = new CardViewAdapter();
//        cardViewAdapter.setOnItemSelectedListener(OnItemSelectedListener);
//        RecyclerView recyclerView = findViewById(R.id.toDoInfo);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(cardViewAdapter);
//        FloatingActionButton add = findViewById(R.id.addBtn);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                add();
//            }
//        });
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_to_do_main, menu);
        item = menu.findItem(   R.id.menu_edit);
        return super.onCreateOptionsMenu(menu);
    }
    
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                if(item.getTitle().equals("edit")) {
                    item.setIcon(R.drawable.ic_accept);
                    item.setTitle("accept");
                    MainFragment.add.setVisibility(View.INVISIBLE);
                    status = true;
                }
                else if(item.getTitle().equals("accept")){
                    item.setIcon(R.drawable.ic_pencil);
                    item.setTitle("edit");
                    MainFragment.add.setVisibility(View.VISIBLE);
                    status = false;
                }
                return true;
            default:
                item.setIcon(R.drawable.ic_pencil);
                return super.onOptionsItemSelected(item);
        }
    }



    //
//    public void add(){
////        Intent intent = new Intent(this, CreatEdit.class);
////        startActivityForResult(intent, REQUEST_ADDITEM);
//    }
//
//    public void edit(Info info){
//        Intent edit = new Intent(this, CreatEdit.class);
//        edit.putExtra(CreatEdit.KEY ,info);
//        startActivityForResult(edit, REQUEST_EDIT);
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//            case REQUEST_ADDITEM:
//                if(resultCode == RESULT_OK){
//                    Info info = data.getParcelableExtra(CreatEdit.KEY);
//                    cardViewAdapter.addData(info);
//                }
//                break;
//            case REQUEST_EDIT:
//                if(resultCode == RESULT_OK){
//                    Info info = data.getParcelableExtra((CreatEdit.KEY));
//                    cardViewAdapter.updateData(info);
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
