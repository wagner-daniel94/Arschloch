package com.example.arschloch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity {

    Spieler spieler1;
    Spieler spieler2;
    Spieler spieler3;
    Spieler spieler4;
    Collection<Spielkarte> ausgeteilte_Spielkarten;
    int playersTurn;

    TextView spieler1TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button austeilenBtn =(Button) findViewById(R.id.austeilenBtn);
        spieler1TV = (TextView)findViewById(R.id.spieler1TV);

        karten_austeilen();
        austeilenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spieler1TV.setText("");

                karten_austeilen();
                playersTurn = get_firstPlayer();

            }
        });

    }

    private void karten_austeilen(){
        int i = 0;
        int zufall_wert;
        int zufall_symbol;
        Spielkarte ausgewaehlte_spielkarte;
        ausgeteilte_Spielkarten = new ArrayList<Spielkarte>();
        spieler1 = new Spieler(new ArrayList<Spielkarte>());
        spieler2 = new Spieler(new ArrayList<Spielkarte>());
        spieler3 = new Spieler(new ArrayList<Spielkarte>());
        spieler4 = new Spieler(new ArrayList<Spielkarte>());
        while(true)
        {

            zufall_wert = (int)(Math.random()*13);
            zufall_symbol = (int)(Math.random()*4);

            ausgewaehlte_spielkarte = new Spielkarte(Kartenwert.values()[zufall_wert],Kartensymbol.values()[zufall_symbol]);
            if(!check_Karte_schon_ausgeteilt(ausgewaehlte_spielkarte)) {
                ausgeteilte_Spielkarten.add(ausgewaehlte_spielkarte);

                switch (i) {
                    case 0:
                        spieler1.getSpielkarten().add(ausgewaehlte_spielkarte);
                        break;
                    case 1:
                        spieler2.getSpielkarten().add(ausgewaehlte_spielkarte);
                        break;
                    case 2:
                        spieler3.getSpielkarten().add(ausgewaehlte_spielkarte);
                        break;
                    case 3:
                        spieler4.getSpielkarten().add(ausgewaehlte_spielkarte);
                       break;
                }
                if(i==3)
                    i=0;
                else
                    i++;
            }
            if(ausgeteilte_Spielkarten.size() >=52)
                break;
        }
        String st;
        Collections.sort(spieler1.getSpielkarten());
        for(Spielkarte sk:spieler1.getSpielkarten()){
            st = spieler1TV.getText().toString() + sk.wert + " " + sk.symbol + "\n";
            spieler1TV.setText(st);
        }

    }

    private boolean check_Karte_schon_ausgeteilt(Spielkarte sk){
        for(Spielkarte k: ausgeteilte_Spielkarten){
            if(k.wert == sk.wert && k.symbol == sk.symbol){
                return true;
            }
        }
        return false;
    }

    private int get_firstPlayer(){
        if(spieler1.isArschloch())
            return 1;
        else if(spieler2.isArschloch())
            return  2;
        else if(spieler3.isArschloch())
            return  3;
        else if(spieler4.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }
}
