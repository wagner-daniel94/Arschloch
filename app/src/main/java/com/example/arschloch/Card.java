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
    private String imageString;
    Drawable drawable;



    public Card(Card_value value, Card_symbol symbol, Drawable drawable){
        this.value = value;
        this.symbol = symbol;
        this.drawable = drawable;
        //imageString = "@mipmap/" + symbol.toString()+value.toString();

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

    public String getImageString(){
        return this.imageString;
    }
}
