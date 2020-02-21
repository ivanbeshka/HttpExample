package com.example.httpexample;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httpexample.databinding.ItemBinding;
import com.example.httpexample.domain.User;

import java.util.ArrayList;

public class RecyclerViewUsersAdapter extends RecyclerView.Adapter<ViewHolderUser> {

    private ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> users) {
        this.users.clear();
        this.users.addAll(users);
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item,
                parent, false);

        return new ViewHolderUser(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

class ViewHolderUser extends RecyclerView.ViewHolder {

    private ItemBinding itemBinding;

    ViewHolderUser(ItemBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }

    void bind(User user) {
        itemBinding.setUser(user);
        itemBinding.executePendingBindings();
    }
}
