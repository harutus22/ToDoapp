package com.example.apple.todoapp.database;

 class DataBaseTable {
    static final String TABLE_NAME = "ToDoTable";

    static final String COLUMN_ID = "id";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_PRIORITY = "priority";

    static final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME +
            "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_TITLE + " TEXT NOT NULL," +
            COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            COLUMN_DATE + " TEXT NOT NULL," +
            COLUMN_PRIORITY + " TEXT NOT NULL" +
            ")";
}
