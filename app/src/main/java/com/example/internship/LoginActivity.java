package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internship.database.AppDataBase;
import com.example.internship.entity.User;


public class LoginActivity extends AppCompatActivity {


    public static final String EMAIL_KEY = "EMAIL";
    public static final String NAME_KEY = "NAME";

    public static final String PWD_KEY = "PWD";
    public static final String FNAME_KEY = "Name";



    public static final String sharedPrefFile = "tn.esprit.intership";



    private AppDataBase database ;


    private EditText edSignInEmail, edSignInPassword;


    public static User currentUser,roleUser;
    private SharedPreferences mPreferences;

    private static final String EMAIL_PATTERN= "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    TextView register;

    SharedPreferences myPref;
    public static final String SharedPreFile = "MyFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = AppDataBase.getAppDatabase(this);

        edSignInEmail = findViewById(R.id.email);
        edSignInPassword = findViewById(R.id.password);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        edSignInEmail.setText(mPreferences.getString(EMAIL_KEY,"") );
        edSignInPassword.setText(mPreferences.getString(PWD_KEY,"") );

        myPref = getSharedPreferences(SharedPreFile,MODE_PRIVATE);

        //TODO 1 get SharedPref And set EditText
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                register(v);
            }
        });



    }



    public void doSignIn(View view) {
        if(validator()) {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(EMAIL_KEY, edSignInEmail.getText().toString());
            preferencesEditor.putString(PWD_KEY, edSignInPassword.getText().toString());
            preferencesEditor.putString(NAME_KEY, currentUser.getName());
            preferencesEditor.apply();
            System.out.println("currentUser.getRole() "+ currentUser.getRole());
            if(currentUser.getRole().equals("Admin")){


                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email",edSignInEmail.getText().toString());
                intent.putExtra("name", currentUser.getName());


                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email",edSignInEmail.getText().toString());
                intent.putExtra("name", currentUser.getName());


                startActivity(intent);
                finish();

            }
        }}

    public boolean validator(){

        if (edSignInEmail.getText().toString().length() == 0
                || edSignInPassword.getText().toString().length() == 0 ){
            Toast.makeText(this, "Email and password must not be empty !", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!edSignInEmail.getText().toString().matches(EMAIL_PATTERN)){
            Toast.makeText(this, "Email is not valid !", Toast.LENGTH_SHORT).show();
            return false;
        }

//        //TODO 2 Check user existance in database

        currentUser = database.userDao()
                .findByEmailAndPassword(
                        edSignInEmail.getText().toString(),
                        edSignInPassword.getText().toString());

        if (currentUser == null){
            Toast.makeText(this, "Wrong credential !", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    public void register(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);

    }
}
