package com.example.httpexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.httpexample.domain.User;
import com.example.httpexample.interactor.UserTask;
import com.example.httpexample.repository.JsonPlaceholderParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserTask.UserResponse {

    RecyclerView recyclerView;
    RecyclerViewUsersAdapter adapter;
    FloatingActionButton fab;
    UserTask userTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.floatingActionButton);
        adapter = new RecyclerViewUsersAdapter();
        userTask = new UserTask(new JsonPlaceholderParser(), this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTask.execute();
            }
        });

    }

    @Override
    public void response(ArrayList<User> users) {
        adapter.setUsers(users);
        recyclerView.setAdapter(adapter);
        Log.d("users", "set");
    }
}

