package com.example.arschloch;

import android.support.v7.app.AppCompatActivity;


public class Statistic extends AppCompatActivity {



  private int statisticNumber;
  private String statisticName;



    public Statistic(int statisticNumber, String statisticName) {
        this.statisticNumber = statisticNumber;
        this.statisticName = statisticName;
    }

    public int getStatisticNumber() {
        return statisticNumber;
    }

    public void setStatisticNumber(int statisticNumber) {
        this.statisticNumber = statisticNumber;
    }

    public String getStatisticName() {
        return statisticName;
    }

    public void setStatisticName(String statisticName) {
        this.statisticName = statisticName;
    }



}
