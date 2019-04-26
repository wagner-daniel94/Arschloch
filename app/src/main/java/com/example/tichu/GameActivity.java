package com.example.tichu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity {

    Collection<Spielkarte> kartenSp1;
    Collection<Spielkarte> kartenSp2;
    Collection<Spielkarte> kartenSp3;
    Collection<Spielkarte> kartenSp4;
    Collection<Spielkarte> ausgeteilte_Spielkarten;


    TextView spieler1TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button austeilenBtn =(Button) findViewById(R.id.austeilenBtn);
        spieler1TV = (TextView)findViewById(R.id.spieler1TV);

        spieler1TV.setText("");


        karten_austeilen();
        austeilenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spieler1TV.setText("");


                karten_austeilen();
            }
        });

    }

    private void karten_austeilen(){
        int i = 0;
        int zufall_wert;
        int zufall_symbol;
        Spielkarte ausgewaehlte_spielkarte;
        ausgeteilte_Spielkarten = new ArrayList<Spielkarte>();
        List<Spielkarte> kartenSp1 = new ArrayList<Spielkarte>();
        Collection<Spielkarte> kartenSp2 = new ArrayList<Spielkarte>();
        Collection<Spielkarte> kartenSp3 = new ArrayList<Spielkarte>();
        Collection<Spielkarte> kartenSp4 = new ArrayList<Spielkarte>();
        while(true)
        {

            zufall_wert = (int)(Math.random()*17);
            if(!check_Sonderkarte(zufall_wert))
            zufall_symbol = (int)(Math.random()*4);
            else
                zufall_symbol = 4;

            ausgewaehlte_spielkarte = new Spielkarte(Kartenwert.values()[zufall_wert],Kartensymbol.values()[zufall_symbol],0);
            if(!check_Karte_schon_ausgeteilt(ausgewaehlte_spielkarte)) {
                ausgeteilte_Spielkarten.add(ausgewaehlte_spielkarte);

                switch (i) {
                    case 0:
                        kartenSp1.add(ausgewaehlte_spielkarte);
                        break;
                    case 1:
                        kartenSp2.add(ausgewaehlte_spielkarte);
                        break;
                    case 2:
                        kartenSp3.add(ausgewaehlte_spielkarte);
                        break;
                    case 3:
                        kartenSp4.add(ausgewaehlte_spielkarte);
                       break;
                }
                if(i==3)
                    i=0;
                else
                    i++;
            }
            if(ausgeteilte_Spielkarten.size() >=56)
                break;
        }
        String st;
        Collections.sort(kartenSp1);
        for(Spielkarte sk:kartenSp1){
            st = spieler1TV.getText().toString() + sk.wert + " " + sk.symbol + "\n";
            spieler1TV.setText(st);
        }

    }

    private boolean check_Sonderkarte(int i){
        if(i == 0 || i == 14 || i == 15 || i == 16)
            return true;
        else
            return false;
    }
    private boolean check_Karte_schon_ausgeteilt(Spielkarte sk){
        for(Spielkarte k: ausgeteilte_Spielkarten){
            if(k.wert == sk.wert && k.symbol == sk.symbol){
                return true;
            }
        }
        return false;
    }
}
