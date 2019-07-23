package com.example.arschloch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticsListAdapter extends ArrayAdapter <Statistic>{
    private static final String TAG = "StatisticsListAdapter";

    private Context mContext;
    private int mRessource;

  // einsetzten des Objektes Statistic um es in die Liustview ein zu fügen

    public StatisticsListAdapter(Context context, int resource, ArrayList<Statistic> objects) {
        super(context, resource, objects);
        mContext = context;
        mRessource = resource;

    }


    public View getView(int position, View convertView, ViewGroup parent){
            String text = "Error";
            String zahl = "Error";
            int id;
             try {
                text = getItem(position).getStatisticName();
                zahl = getItem(position).getStatisticNumber();
                 id = getItem(position).getStatisticId();
            }catch (NullPointerException e){

            }
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mRessource, parent, false);

            TextView tvText =  convertView.findViewById(R.id.statisticName);
            TextView tvZahl =  convertView.findViewById(R.id.statisticNumber);

            tvText.setText(text);
            tvZahl.setText(zahl);

            return convertView;




    }
}
