/*
Änderung: Automatisches Drücken der Karten ab 2. Runde
Datum: 06.05.2019
Autor: Konieczek
 */

package com.example.arschloch;
import java.util.Collections;

public class Druecken {
    //erst ab zweiter runde -> Rundencounter
    //wunsch vom Gewinner einbauern



    public void arschlochAutoDruecken (Player arschloch, Player winner, Card wishCard){
        //Vorausgesetzt karten sind sortiert, höchste an letzter Stelle

        if(wishCard != null){

        }


        Card highestCard1 = arschloch.getCards().get(arschloch.getCards().size()-1);
        Card highestCard2 = arschloch.getCards().get(arschloch.getCards().size()-2);

        winner.getCards().add(highestCard1);
        winner.getCards().add(highestCard2);
        arschloch.getCards().remove(highestCard1);
        arschloch.getCards().remove(highestCard2);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());


    }
    public void winnerAutoDruecken (Player arschloch, Player winner){

        /*wenn lowestCard1 oder lowestCard2 Teil einer Straße oder Kombination, dann neue "höhere" Karte nehmen
        **außer wenn die neue Karte höher als 10 ist
        */

        Card lowestCard1 = winner.getCards().get(0);
        Card lowestCard2 = arschloch.getCards().get(1);


        arschloch.getCards().add(lowestCard1);
        arschloch.getCards().add(lowestCard2);
        winner.getCards().remove(lowestCard1);
        winner.getCards().remove(lowestCard2);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());
    }

    public void winnerDruecken (Player arschloch, Player winner){
        Card choosenCard1;
        Card choosenCard2;

        // winner kann 2 Karten auswählen

        arschloch.getCards().add(choosenCard1);
        arschloch.getCards().add(choosenCard2);
        winner.getCards().remove(choosenCard1);
        winner.getCards().remove(choosenCard2);
        Collections.sort(arschloch.getCards());
        Collections.sort(winner.getCards());

        //Karten neu anzeigen

        //Bildschirm updaten
        //invalidate() bei UI Thread
        //postInvalidate() bei non UI Thread
    }
}
