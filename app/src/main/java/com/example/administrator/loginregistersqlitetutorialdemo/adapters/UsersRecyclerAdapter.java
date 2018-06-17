package com.example.administrator.loginregistersqlitetutorialdemo.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.loginregistersqlitetutorialdemo.R;
import com.example.administrator.loginregistersqlitetutorialdemo.model.User;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_1,
        ITEM_TYPE_2
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_1.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler, parent, false);

            /** 自定義 RecyclerView的Item的layout_weight */
            int height = (int) (parent.getMeasuredHeight() / 4);
            int width = parent.getMeasuredWidth();
            view.setLayoutParams(new RecyclerView.LayoutParams(width, height));

            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler2, parent, false);

            /** 自定義 RecyclerView的Item的layout_weight */
            int height = parent.getMeasuredHeight() / 5;
            int width = parent.getMeasuredWidth();
            view.setLayoutParams(new RecyclerView.LayoutParams(width, height));

            return new UserViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_1.ordinal() : ITEM_TYPE.ITEM_TYPE_2.ordinal();
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
        }
    }
}
