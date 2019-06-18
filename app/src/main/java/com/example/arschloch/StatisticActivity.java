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
       ListView mListView = (ListView) findViewById(R.id.statisticsList);
        //erstellen der Statistik Instanzen. noch abändern zu nicht hardcoded Zahlen.

       MyDatabaseHelper db = new MyDatabaseHelper(this);
       db.createDefaultStatisticsIfNeed();



        List<Statistic> statistics = db.getAllStatistics();
        this.statisticsList.addAll(statistics);



        statisticsListAdapter adapter = new statisticsListAdapter (this, R.layout.activity_statistic, statisticsList);
        mListView.setAdapter(adapter);





    }
}
