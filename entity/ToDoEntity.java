package com.example.apple.todoapp.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "todo1")
public class ToDoEntity {

    @PrimaryKey()
    public String id;
    public String title;
    public String description;
    public String date;
    public String priority;
}
