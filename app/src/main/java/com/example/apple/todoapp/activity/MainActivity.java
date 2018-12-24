package com.example.apple.todoapp.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.apple.todoapp.R;
import com.example.apple.todoapp.adapters.CardViewAdapter;
import com.example.apple.todoapp.viewType.Info;

public class MainActivity extends AppCompatActivity {
    private CardViewAdapter cardViewAdapter;
    private final int REQUEST_ADDITEM = 8;
    private final int REQUEST_EDIT = 6;

    private CardViewAdapter.OnItemSelectedListener OnItemSelectedListener = new CardViewAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(Info info) {
            edit(info);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton add = findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        cardViewAdapter = new CardViewAdapter();
        cardViewAdapter.setOnItemSelectedListener(OnItemSelectedListener);
        RecyclerView recyclerView = findViewById(R.id.toDoInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cardViewAdapter);
    }

    public void add(){
        Intent intent = new Intent(this, CreatEdit.class);
        startActivityForResult(intent, REQUEST_ADDITEM);
    }

    public void edit(Info info){
        Intent edit = new Intent(this, CreatEdit.class);
        edit.putExtra(CreatEdit.KEY ,info);
        startActivityForResult(edit, REQUEST_EDIT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_ADDITEM:
                if(resultCode == RESULT_OK){
                    Info info = data.getParcelableExtra(CreatEdit.KEY);
                    cardViewAdapter.addData(info);
                }
                break;
            case REQUEST_EDIT:
                if(resultCode == RESULT_OK){
                    Info info = data.getParcelableExtra((CreatEdit.KEY));
                    cardViewAdapter.updateData(info);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
