package com.example.arschloch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class statisticsListAdapter extends ArrayAdapter <Statistic>{
    private static final String TAG = "statisticsListAdapter";

    private Context mContext;
    int mRessource;

  // einsetzten des Objektes Statistic um es in die Liustview ein zu fügen

    public statisticsListAdapter(Context context, int resource,  ArrayList<Statistic> objects) {
        super(context, resource, objects);
        mContext = context;
        mRessource = resource;

    }


    public View getView(int position, View convertView, ViewGroup parent){
        String text = getItem(position).getStatisticName();
        String zahl = getItem(position).getStatisticNumber();
        int id = getItem(position).getStatisticId();
        //Statistic statistic = new Statistic(id,zahl,text);
       // String idHelper = String.valueOf(id);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mRessource,parent,false);

       // TextView tvId = (TextView) convertView.findViewById(R.id.textView0);
        TextView tvText = (TextView) convertView.findViewById(R.id.statisticName);
        TextView tvZahl = (TextView) convertView.findViewById(R.id.statisticNumber);

     //   tvId.setText(idHelper);
        tvText.setText(text);
        tvZahl.setText(zahl);

        return convertView;




    }
}
