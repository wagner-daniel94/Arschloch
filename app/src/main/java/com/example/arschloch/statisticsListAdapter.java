package com.example.arschloch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class statisticsListAdapter extends ArrayAdapter <Statistic>{
    private static final String TAG = "statisticsListAdapter";

    private Context mContext;
    int mRessource;


    public statisticsListAdapter(Context context, int resource,  ArrayList<Statistic> objects) {
        super(context, resource, objects);
        mContext = context;
        mRessource = resource;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        String text = getItem(position).getStatisticName();
        String zahl = getItem(position).getStatisticNumber();

        Statistic statistic = new Statistic(zahl,text);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRessource,parent,false);

        TextView tvText = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvZahl = (TextView) convertView.findViewById(R.id.textView2);


        tvText.setText(text);
        tvZahl.setText(zahl);

        return convertView;




    }
}
