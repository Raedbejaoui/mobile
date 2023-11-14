package com.example.internship.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String Name;


    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "role")
    private String role;


    @Ignore
    public User() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Ignore
    public User(String Name) {
        this.Name = Name;

    }

    public User(int uid, String name, String email, String password, String role) {
        this.uid = uid;
        Name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Ignore
    public User(int uid, String Name) {
        this.uid = uid;
        this.Name = Name;

    }

    public User(String Name,  String email, String password) {
        this.Name = Name;

        this.email = email;
        this.password = password;
        ;
    }

    public User(String name, String email, String password, String role) {
        Name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Ignore
    public User(int uid, String Name, String email, String password) {
        this.uid = uid;
        this.Name = Name;

        this.email = email;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", Name='" + Name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
