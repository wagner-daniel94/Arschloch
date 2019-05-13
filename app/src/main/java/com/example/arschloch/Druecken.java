/*
Änderung: Automatisches Drücken der Karten ab 2. Runde
Datum: 06.05.2019
Autor: Konieczek
 */

package com.example.arschloch;
import java.util.Collections;

public class Druecken {
    //erst ab zweiter runde -> Rundencounter

    //Level 2 es werden keine Päärchen aufgebrochen beim Drücken
    boolean level2 =  false;

    public void wunschKarte(Player arschloch, Player winner, Card wishCard){
        //Wunschkarte geben, wenn das Arschloch sie hat
        if(wishCard != null && arschloch.getCards().contains(wishCard) ){
            winner.getCards().add(wishCard);
            arschloch.getCards().remove(wishCard);
            Collections.sort(arschloch.getCards());
            Collections.sort(winner.getCards());
        }
    }

    public void arschlochAutoDruecken (Player arschloch, Player winner){
        //Vorausgesetzt karten sind sortiert, höchste an letzter Stelle
        Card highestCard1 = arschloch.getCards().get(arschloch.getCards().size()-1);

        //Karten Tausch und Sortieren der Handkarten
        winner.getCards().add(highestCard1);
        arschloch.getCards().remove(highestCard1);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());
    }


    public void winnerAutoDruecken (Player arschloch, Player winner){
        Card lowestCard1 = winner.getCards().get(0);

        /*
        Wenn level2 aktiviert
        und die Karte in einer Kombination vorhanden ist,
        wird diese nicht weggegben
        Boy diese Code ist hässlich
        */

        if(level2){
            Card otherCard = winner.getCards().get(0);

            //wenn die Karte i nicht mehrfach vorhanden ist wird sie, gedrückt
            boolean containsCardValue = false;
            for( int i = 0; i<winner.getCards().size();i++){
                otherCard = winner.getCards().get(i);
                winner.getCards().remove(otherCard);
                for(int j = 1; i<winner.getCards().size();j++){
                    if(otherCard.getValue() == winner.getCards().get(j).getValue()){
                        containsCardValue = true;
                        break;
                    }
                }
                if(!containsCardValue){
                    break;
                }
                winner.getCards().add(otherCard);
            }
            //wenn die Karte mehrfach vorkommt und höher als 10 ist, wird die niedrigste karte gedrückt
            if(otherCard.getValue().compareTo(Card_value.ten)>0){
                otherCard = winner.getCards().get(0);
            }
            lowestCard1 = otherCard;
        }

        //Karten Tausch und Sortieren der Handkarten
        arschloch.getCards().add(lowestCard1);
        winner.getCards().remove(lowestCard1);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());
    }

    /*
    public void winnerDruecken (Player arschloch, Player winner){
        Card choosenCard1;

        // winner kann 1 Karten auswählen

        arschloch.getCards().add(choosenCard1);
        winner.getCards().remove(choosenCard1);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());

        //Karten neu anzeigen

        //Bildschirm updaten
        //invalidate() bei UI Thread
        //postInvalidate() bei non UI Thread
    }
    */
}
