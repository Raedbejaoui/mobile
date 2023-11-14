package com.example.internship;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internship.database.AppDataBase;
import com.example.internship.entity.File;
import com.example.internship.entity.Intership;

public class applyActivity extends AppCompatActivity {
    private static final int FILE_PICKER_REQUEST_CODE = 1;
    private File selectedFile;
    private static final int PICK_FILE_REQUEST_CODE = 1;

    TextView title;

    SharedPreferences myPref;
    public static final String SharedPreFile = "MyFile";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

            Button btnUploadFile = findViewById(R.id.btnUploadFile);
            title = findViewById(R.id.title);
            btnUploadFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFilePicker();
                }
            });
        }

        private void openFilePicker() {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    // Handle the selected file URI

                    String filePath = uri.getPath(); // You may need to handle different URIs (e.g., content://)
                    String interId = getIntent().getStringExtra("interId");
                    String titlee = getIntent().getStringExtra("title");
                    title.setText(titlee);

                    AppDataBase.getAppDatabase(getApplicationContext()).filesDao().add(new File("", interId,"",filePath));

                    myPref = getSharedPreferences(SharedPreFile,MODE_PRIVATE);

                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.putExtra("email",myPref.getString("EMAIL",""));
                    intent.putExtra("name", myPref.getString("NAME", ""));

                    Toast.makeText(this, "CV Added", Toast.LENGTH_LONG).show();
                    startActivity(intent);




                }
            }
        }





}