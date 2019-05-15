package com.example.arschloch;

import android.support.v7.app.AppCompatActivity;


public class Statistic extends AppCompatActivity {



  private String statisticNumber;
  private String statisticName;



    public Statistic(String statisticNumber, String statisticName) {
        this.statisticNumber = statisticNumber;
        this.statisticName = statisticName;
    }

    public String getStatisticNumber() {
        return statisticNumber;
    }

    public void setStatisticNumber(String statisticNumber) {
        this.statisticNumber = statisticNumber;
    }

    public String getStatisticName() {
        return statisticName;
    }

    public void setStatisticName(String statisticName) {
        this.statisticName = statisticName;
    }



}
