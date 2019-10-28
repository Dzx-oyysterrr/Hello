package com.example.hello;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class Myada extends ArrayAdapter {

    private static final String TAG="MyAdapter";

    public Myada(Context context, int resource, ArrayList<HashMap<String,String>>list)
    {
        super(context,resource,list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list,
                    parent,
                    false);
        }
        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.itemTitle);
        TextView detail = (TextView) itemView.findViewById(R.id.Detail);
        title.setText("Title:" + map.get("country"));
        detail.setText("detail:" + map.get("ratio"));
        return itemView;
    }


}
