package com.example.internship;

import static android.content.Context.MODE_PRIVATE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.internship.adapter.IntershipListAdapter;
import com.example.internship.database.AppDataBase;
import com.example.internship.entity.Intership;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

    public class WelcomeActivity  extends Fragment {
        private RecyclerView recyclerView;
        private FloatingActionButton add;

        SharedPreferences myPref;
        public static final String SharedPreFile = "MyFile";


        @SuppressLint("MissingInflatedId")
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View root=  inflater.inflate(R.layout.activity_welcome, container, false);
            recyclerView = root.findViewById(R.id.recyclerView);
            add = root.findViewById(R.id.add);


            add.setOnClickListener(e->{
                Intent i = new Intent(getActivity(), addIntershipActivity.class);
                startActivity(i);
            });

            myPref = getContext().getSharedPreferences(SharedPreFile,MODE_PRIVATE);

            String value = myPref.getString("role",null);
            if (value != "Admin") {
                add.setVisibility(View.INVISIBLE);

            } else {
            }


            ArrayList<Intership> entity1s = new ArrayList<>(); //intanciation de la liste
            entity1s = (ArrayList<Intership>) AppDataBase.getAppDatabase(getActivity()).intershipDao().getall(); //recuperation des recs
            IntershipListAdapter adapter = new IntershipListAdapter(entity1s); //instanciation de l'adapter
            recyclerView.setAdapter(adapter); //param de l'rv
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

            return root;


        }
    }