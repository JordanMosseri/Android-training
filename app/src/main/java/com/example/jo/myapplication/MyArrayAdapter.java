package com.example.jo.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jo.myapplication.model.Message;

import java.util.List;

/**
 * Created by Jo on 22/03/2015.
 */
public class MyArrayAdapter extends ArrayAdapter<Message> {

    List<String> list;

    public MyArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv = new TextView(parent.getContext());
        tv.setText(list.get(position).toString());
        return tv;
    }
}
