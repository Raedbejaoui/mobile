package com.example.internship.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@androidx.room.Entity
public class File {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userId;
    private String interId;
    private String fileName;
    private String filePath;

    // Add getters and setters


    public File(String userId, String interId, String fileName, String filePath) {
        this.userId = userId;
        this.interId = interId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInterId() {
        return interId;
    }

    public void setInterId(String jobId) {
        this.interId = interId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
