package com.example.arschloch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StatisticActivity extends AppCompatActivity {


    ArrayList<Statistic> statisticsList = new ArrayList<>();

    //  private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisticmain);
        //Log.d(TAG, "onCreate: Started. ");
       ListView mListView = (ListView) findViewById(R.id.myListView);
        //erstellen der Statistik Instanzen. noch abändern zu nicht hardcoded Zahlen.

       MyDatabaseHelper db = new MyDatabaseHelper(this);
       db.createDefaultStatisticsIfNeed();



        List<Statistic> statistics = db.getAllStatistics();
        this.statisticsList.addAll(statistics);


        /* Statistic statistics  = new Statistic(0,"1","Anzahl Spiele");
        Statistic statistics1 = new Statistic(1,"2","Anzahl gewonnene Spiele");
        Statistic statistics2 = new Statistic(2,"3","Gewinnrate");
        Statistic statistics3 = new Statistic(3,"4","Anzahl der Züge");
        Statistic statistics4 = new Statistic(4,"5","Anzahl verlorener Spiele");


        statisticsList.add(statistics);
        statisticsList.add(statistics1);
        statisticsList.add(statistics2);
        statisticsList.add(statistics3);
        statisticsList.add(statistics4);
        */
        statisticsListAdapter adapter = new statisticsListAdapter (this, R.layout.activity_statistic, statisticsList);
        mListView.setAdapter(adapter);





    }
}
