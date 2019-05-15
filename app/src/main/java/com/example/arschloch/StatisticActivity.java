package com.example.arschloch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity {

  //  private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        //Log.d(TAG, "onCreate: Started. ");
       ListView mListView = (ListView) findViewById(R.id.listView);

        Statistic statistics  = new Statistic("1","Anzahl Spiele");
        Statistic statistics1 = new Statistic("2","Anzahl gewonnene Spiele");
        Statistic statistics2 = new Statistic("3","Gewinnrate");
        Statistic statistics3 = new Statistic("4","Anzahl der Züge");
        Statistic statistics4 = new Statistic("5","Anzahl verlorener Spiele");

        ArrayList<Statistic> statisticsList = new ArrayList<>();

        statisticsList.add(statistics);
        statisticsList.add(statistics1);
        statisticsList.add(statistics2);
        statisticsList.add(statistics3);
        statisticsList.add(statistics4);

        statisticsListAdapter adapter = new statisticsListAdapter (this, R.layout.activity_statistic, statisticsList);
        mListView.setAdapter(adapter);





    }
}
