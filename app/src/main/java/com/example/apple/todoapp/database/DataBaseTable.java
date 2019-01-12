package com.example.apple.todoapp.database;

public class DataBaseTable {
    public static final String TABLE_NAME = "ToDoTable";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PRIORITY = "priority";

    public static final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_TITLE + " TEXT NOT NULL," +
            COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            COLUMN_DATE + " TEXT NOT NULL," +
            COLUMN_PRIORITY + " TEXT NOT NULL" +
            ")";
}
