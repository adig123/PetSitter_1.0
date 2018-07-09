package com.example.bonimoo_imac_one.app.src.main;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BaseAdapter extends android.widget.BaseAdapter {

    Context mCtx;
    ArrayList<User>users = new ArrayList<>();
    Boolean status;

    public BaseAdapter(Context mCtx, ArrayList<User> users){
        this.mCtx = mCtx;
        this.users = users;
    }

    public void refrash(ArrayList<User>users){
        this.users = users;
            notifyDataSetChanged();


    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView =LayoutInflater.from(mCtx).inflate(R.layout.users_layout, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textView);
        final TextView textView1 = convertView.findViewById(R.id.textView2);
        Button button = convertView.findViewById(R.id.button2);
        ConstraintLayout constraintLayout = convertView.findViewById(R.id.layout);

        textView.setText(users.get(position).name);
        status = users.get(position).status;
            textView1.setText("Online");
            textView1.setTextColor(mCtx.getResources().getColor(R.color.greenColor));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText("Offline");
                textView1.setTextColor(mCtx.getResources().getColor(R.color.redColor));
                users.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
