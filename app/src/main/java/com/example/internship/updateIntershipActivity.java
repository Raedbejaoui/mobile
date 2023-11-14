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

public class updateIntershipActivity extends AppCompatActivity {
    TextView textViewTitle, textViewDescription;
    Button update;
    int ituId;
    SharedPreferences myPref;
    public static final String SharedPreFile = "MyFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_intership);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        update = findViewById(R.id.add);

        //Collect data from intent to display

        textViewTitle.setText(getIntent().getStringExtra("attr1"));
        textViewDescription.setText(getIntent().getStringExtra("attr2"));
        ituId = getIntent().getIntExtra("ituId", -1);
        //bring instance from DB
        Intership itu = AppDataBase.getAppDatabase(this).intershipDao().getbyid(ituId);

        update.setOnClickListener(e -> {
            myPref = getSharedPreferences(SharedPreFile,MODE_PRIVATE);

            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("email",myPref.getString("EMAIL",""));
            i.putExtra("name", myPref.getString("NAME", ""));


            itu.setTitle(textViewTitle.getText().toString());
            itu.setDescription(textViewDescription.getText().toString());
            AppDataBase.getAppDatabase(e.getContext()).intershipDao().update(itu);
            Toast.makeText(e.getContext(), "updated", Toast.LENGTH_LONG).show();
            startActivity(i);


        });

    }
}
