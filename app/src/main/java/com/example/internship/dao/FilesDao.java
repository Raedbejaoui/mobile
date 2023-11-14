package com.example.internship.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.internship.entity.File;

import java.util.List;

@Dao
public interface FilesDao {

    @Insert
    void add(File fileEntity);

    @Query("SELECT * FROM File")
    List<File> getAllFiles();
}
