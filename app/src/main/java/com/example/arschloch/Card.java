/*
Änderung: Anlegen der Klasse Card
Datum: 04.04.2019
Autor: Wagner
 */

package com.example.arschloch;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Card implements Comparable<Card>{
    private Card_value value;
    private Card_symbol symbol;
    private boolean isMarked;

    int resourceId;



    public Card(Card_value value, Card_symbol symbol, int resourceId){
        this.value = value;
        this.symbol = symbol;
        this.resourceId = resourceId;

    }

    @Override
    public int compareTo(Card o) {
        return value.compareTo(o.value);
    }

    public Card_value getValue() {
        return value;
    }

    public void setValue(Card_value value) {
        this.value = value;
    }

    public Card_symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Card_symbol symbol) {
        this.symbol = symbol;
    }

    public int getResourceId(){ return this.resourceId; }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
