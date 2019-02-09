package com.example.apple.todoapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.apple.todoapp.database.entity.ToDoEntity;
import com.example.apple.todoapp.viewType.Info;

import java.util.List;

@Dao
public interface ToDoDao {

    @Query("select * from todo")
    List<Info> findAll();

    @Insert()
    void insert(Info info);

    @Update
    void update(Info... infos);

    @Delete
    void delete(Info... infos);
}
