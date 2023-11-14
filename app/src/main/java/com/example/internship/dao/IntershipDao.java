package com.example.internship.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.internship.entity.Intership;

import java.util.List;

@Dao
public interface IntershipDao  {
    @Insert
    void add(Intership e1);
    @Update
    void update(Intership e1);
    @Delete
    void delete(Intership e1);
    @Query("select * from Intership")
    List<Intership> getall();
    @Query("select * from Intership where id= :id")
    Intership getbyid(int id);}