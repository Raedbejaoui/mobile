package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;




        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.navigation.NavController;
        import androidx.navigation.Navigation;
        import androidx.navigation.ui.AppBarConfiguration;
        import androidx.navigation.ui.NavigationUI;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static final String EMAIL_KEY = "EMAIL";

    public static final String PWD_KEY = "PWD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        Toolbar tolbar = findViewById(R.id.toolbar);




        Button Btnlogout = findViewById(R.id.btnlogout);




        setSupportActionBar(tolbar);
        tolbar.setNavigationIcon(null);          // to hide Navigation icon

        DrawerLayout drawer =findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textname);
        TextView navUseremail = (TextView) headerView.findViewById(R.id.textemail);

        navUsername.setText("Name :"+ getIntent().getStringExtra("name").toString());
        navUseremail.setText("Email :"+getIntent().getStringExtra("email").toString());



        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_restaurant, R.id.nav_add_restaurant, R.id.nav_users)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences SM = getSharedPreferences("tn.esprit.intership", 0);
                SharedPreferences.Editor edit = SM.edit();
                edit.putString(EMAIL_KEY," ");
                edit.putString(PWD_KEY," ");
                edit.commit();

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
