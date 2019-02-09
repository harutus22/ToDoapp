package com.example.apple.todoapp.database;

import android.arch.persistence.room.Database;

import com.example.apple.todoapp.database.dao.ToDoDao;
import com.example.apple.todoapp.viewType.Info;

@Database(entities = Info.class, version = 1, exportSchema = false)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {
    public abstract ToDoDao toDoDao();
}
