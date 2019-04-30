/*
Änderung: Anlegen der Klasse Spielkarte
Datum: 04.04.2019
Autor: Wagner
 */

package com.example.arschloch;

public class Spielkarte implements Comparable<Spielkarte>{
    Kartenwert wert;
    Kartensymbol symbol;


    public Spielkarte(Kartenwert wert,Kartensymbol symbol){
        this.wert = wert;
        this.symbol = symbol;
    }

    @Override
    public int compareTo(Spielkarte o) {
        return wert.compareTo(o.wert);
    }
}
