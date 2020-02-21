package com.example.httpexample.interactor;

import android.os.AsyncTask;
import android.util.Log;

import com.example.httpexample.RecyclerViewUsersAdapter;
import com.example.httpexample.domain.User;
import com.example.httpexample.repository.JsonPlaceholderParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UserTask extends AsyncTask<User, ArrayList<User>, ArrayList<User>> {
    JsonPlaceholderParser parser;
    UserResponse userResponse;

    public UserTask(JsonPlaceholderParser parser, UserResponse userResponse) {
        this.parser = parser;
        this.userResponse = userResponse;
    }

    @Override
    protected ArrayList<User> doInBackground(User[] objects) {
        try {
            ArrayList<User> users = parser.getUsers();
            Log.d("Users", users.toString());
            return users;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("USERS", "Ошибка парсинга " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<User> users) {
        super.onPostExecute(users);
        Log.d("Users", "post ok");
        userResponse.response(users);
    }

    public interface UserResponse {
        void response(ArrayList<User> users);
    }
}

