package com.example.httpexample.interactor;

import android.widget.TextView;

import com.example.httpexample.domain.User;
import com.example.httpexample.repository.JsonPlaceholderParser;

import java.util.ArrayList;

public class UserInteractor implements UserResponse {

    JsonPlaceholderParser parser = new JsonPlaceholderParser();

    public void getUsers() {
        new UserTask(parser, this).execute();
    }

    @Override
    public void response(ArrayList<User> users) {

    }
}

interface UserResponse {
    void response(ArrayList<User> users);
}