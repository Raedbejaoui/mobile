package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internship.database.AppDataBase;
import com.example.internship.entity.Intership;

public class addIntershipActivity extends AppCompatActivity {
    TextView textViewTitle;
    TextView textViewDescription;
    Button add;
    SharedPreferences myPref;
    public static final String SharedPreFile = "MyFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intership);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        add = findViewById(R.id.add);
        add.setOnClickListener(e-> {

            myPref = getSharedPreferences(SharedPreFile,MODE_PRIVATE);

            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("email",myPref.getString("EMAIL",""));
            i.putExtra("name", myPref.getString("NAME", ""));


            AppDataBase.getAppDatabase(e.getContext()).intershipDao().add(new Intership(textViewTitle.getText().toString(), textViewDescription.getText().toString()));
            Toast.makeText(e.getContext(), "Added", Toast.LENGTH_LONG).show();
            startActivity(i);
        });

    }
}