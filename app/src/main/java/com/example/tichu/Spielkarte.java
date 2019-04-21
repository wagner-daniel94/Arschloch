/*
Änderung: Anlegen der Klasse Spielkarte
Datum: 04.04.2019
Autor: Wagner
 */

package com.example.tichu;

public class Spielkarte implements Comparable<Spielkarte>{
    Kartenwert wert;
    Kartensymbol symbol;
    int punkte;

    public Spielkarte(Kartenwert wert,Kartensymbol symbol,int punkte){
        this.wert = wert;
        this.symbol = symbol;
        this.punkte = punkte;
    }

    @Override
    public int compareTo(Spielkarte o) {
        return wert.compareTo(o.wert);
    }
}
