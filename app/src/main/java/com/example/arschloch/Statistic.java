package com.example.arschloch;


public class Statistic  {

    //Generieren des Objektes mit Namen und Nummer, welches in einer Liste gespeichert wird um es in den Statistiken auf zu rufen
    //region Variablen
    private int statisticId;
    private String statisticNumber;
     private String statisticName;
    //endregion.

    public Statistic(int statisticId, String statisticNumber, String statisticName) {
        this.statisticNumber = statisticNumber;
        this.statisticName = statisticName;
        this.statisticId = statisticId;
    }

    public Statistic() {

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

    public int getStatisticId(){return statisticId;}
    public void setStatisticId(int statisticId){
        this.statisticId=statisticId;
    }


}
