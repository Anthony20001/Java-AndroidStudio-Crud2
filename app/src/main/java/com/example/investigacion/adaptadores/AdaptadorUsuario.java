package com.example.investigacion.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.investigacion.R;
import com.example.investigacion.database.tables.User;

import java.util.ArrayList;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolderDates> {

    private final ArrayList<User> userList;
    Activity activity;
    ArrayList<String> row;

    public AdaptadorUsuario(Activity activity, ArrayList<User> userList){
        this.userList = userList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdaptadorUsuario.ViewHolderDates onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_row_table_user, parent, false);
        return new ViewHolderDates(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUsuario.ViewHolderDates holder, int position) {
        if(userList != null && userList.size() > 0){
            holder.setDates(userList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderDates extends RecyclerView.ViewHolder {
        TextView id, username, email, password;

        public ViewHolderDates(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.model_table_user_id);
            username = itemView.findViewById(R.id.model_table_user_name);
            email = itemView.findViewById(R.id.model_table_user_email);
            password = itemView.findViewById(R.id.model_table_user_password);
        }

        public void setDates(User user) {
            id.setText(String.valueOf(user.getId()));
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
    }
}
