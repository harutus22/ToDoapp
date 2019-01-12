package com.example.apple.todoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.apple.todoapp.viewType.Info;

import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private ToDoDBHelper toDoDBHelper;
    private List<Info> list = new ArrayList<>();

    public DBManager(Context context) { this.toDoDBHelper = new ToDoDBHelper(context); }

    public long createToDo(Info info){
        SQLiteDatabase database = toDoDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseTable.COLUMN_ID, info.getId());
        values.put(DataBaseTable.COLUMN_TITLE, info.getTitle());
        values.put(DataBaseTable.COLUMN_DESCRIPTION, info.getDescription());
        values.put(DataBaseTable.COLUMN_DATE, info.getDate());
        values.put(DataBaseTable.COLUMN_PRIORITY, info.getPriority());
        return database.insert(DataBaseTable.TABLE_NAME, null, values);
    }

    public void remove(String id){
        SQLiteDatabase database = toDoDBHelper.getReadableDatabase();
        database.delete(DataBaseTable.TABLE_NAME, " id = ? ", new String[]{id});
    }

    public void update(Info info){
        SQLiteDatabase database = toDoDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseTable.COLUMN_ID, info.getId());
        values.put(DataBaseTable.COLUMN_TITLE, info.getTitle());
        values.put(DataBaseTable.COLUMN_DESCRIPTION, info.getDescription());
        values.put(DataBaseTable.COLUMN_DATE, info.getDate());
        values.put(DataBaseTable.COLUMN_PRIORITY, info.getPriority());
        database.update(DataBaseTable.TABLE_NAME, values," id = ? ", new String[]{info.getId()});
    }

    public List<Info> getToDo(){
        SQLiteDatabase database = toDoDBHelper.getReadableDatabase();
        Cursor cursor = database.query(DataBaseTable.TABLE_NAME,null,null,
                null,null,null, null);
        while (cursor.moveToNext()){
            Info info = new Info();
            info.setId(cursor.getString(cursor.getColumnIndex(DataBaseTable.COLUMN_ID)));
            info.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseTable.COLUMN_TITLE)));
            info.setDescription(cursor.getString(cursor.getColumnIndex(DataBaseTable.COLUMN_DESCRIPTION)));
            info.setDate(cursor.getString(cursor.getColumnIndex(DataBaseTable.COLUMN_DATE)));
            info.setPriority(cursor.getString(cursor.getColumnIndex(DataBaseTable.COLUMN_PRIORITY)));
            list.add(info);
        }
        cursor.close();
        return list;
    }
}
