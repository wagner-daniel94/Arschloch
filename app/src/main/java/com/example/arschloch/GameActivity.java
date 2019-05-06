package com.example.arschloch;

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

    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Collection<Card> distributed_cards;
    List<Card> card_deck;
    int playersTurn;
    int amountCardsPlayed = 0;
    Card_value cardValuePlayed;

    TextView spieler1TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button austeilenBtn =(Button) findViewById(R.id.austeilenBtn);
        Button playBtn = (Button) findViewById(R.id.playBtn);
        spieler1TV = (TextView)findViewById(R.id.spieler1TV);

        cards_distributing();
        austeilenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spieler1TV.setText("");

                cards_distributing();
                playersTurn = get_firstPlayer();


            }
        });
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_card(player1);
                show_players_cards();
            }
        });
    }

/*
* Das austeielen der Karten
* */
    private void cards_distributing(){
        int i = 0;
        int c = 0;
        Card create_card;
        distributed_cards = new ArrayList<Card>();
        card_deck = new ArrayList<Card>();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        for(int k= 0;k<=12;k++){
            for(int l = 0;l<=3;l++){
                create_card = new Card(Card_value.values()[k], Card_symbol.values()[l]);
                card_deck.add(create_card);
            }
        }
        Collections.shuffle(card_deck);
        while(true)
        {


                switch (i) {
                    case 0:
                        player1.getCards().add(card_deck.get(c));
                        break;
                    case 1:
                        player2.getCards().add(card_deck.get(c));
                        break;
                    case 2:
                        player3.getCards().add(card_deck.get(c));
                        break;
                    case 3:
                        player4.getCards().add(card_deck.get(c));
                       break;
                }
                if(c!=card_deck.size()-1)
                c++;
                else
                    break;

                //Auswahl des nächsten Spielers, der eine Karte erhält. Nach Spieler4(i==3) ist Spieler1 wieder an der Reihe
                if(i==3)
                    i=0;
                else
                    i++;
            }

        //Sortieren der Kartendecks
        Collections.sort(player1.getCards());
        Collections.sort(player2.getCards());
        Collections.sort(player3.getCards());
        Collections.sort(player4.getCards());
        show_players_cards();

    }

/*
* anzeigen der Karten
* muss noch auf ImageViews umgestellt werden
* */
    private void show_players_cards(){
        String st;
        for(Card c:player1.getCards()){
            st = spieler1TV.getText().toString() + c.getValue() + " " + c.getSymbol() + "\n";
            spieler1TV.setText(st);
        }
    }

/*
* Überprüfun ob erzeugte Karte schon ausgeteilt wurde
* */
    private boolean check_card_already_distributed(Card card){
        for(Card c: distributed_cards){
            if(c.getValue() == card.getValue() && c.getSymbol() == card.getSymbol()){
                return true;
            }
        }
        return false;
    }

/*
* Festlegung des ersten Spielers
* */
    private int get_firstPlayer(){
        //Wenn Spieler Arschloch in der Runde zuvor war beginnt er das Spiel. Wenn es die erste Runde ist entscheidet der Zufall
        if(player1.isArschloch())
            return 1;
        else if(player2.isArschloch())
            return  2;
        else if(player3.isArschloch())
            return  3;
        else if(player4.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }

/*
* Überprüfung ob es eine gültige Kombintion ist
* */
    private boolean check_combination(List<Card> choosen_cards){
        boolean value_statement =true;
        for(int i = 0; i < choosen_cards.size()-1;i++){
            //Die ausgewählten Karten dürfen nur den gleichen Wert besitzen
            if(choosen_cards.get(i).getValue() != choosen_cards.get(i+1).getValue())
                value_statement = false;
        }
        //Die Anzahl der Karten muss mit der Anzahl zuvor gespielter Karten übereinstimmen
        if((choosen_cards.size() == amountCardsPlayed || amountCardsPlayed == 0) && value_statement == true)
            return true;
        else
            return false;
    }

/*
* Das spielen von Karten
* */
    private void play_card(Player p){
        if (amountCardsPlayed == 0){
            List<Card> combination = new ArrayList<Card>();
            Card_value cv = p.getCards().get(0).getValue();
            while (p.getCards().get(0).getValue() == cv){
                combination.add(p.getCards().get(0));
                p.getCards().remove(0);
            }
            if(check_combination(combination)){
                amountCardsPlayed = combination.size();
                cardValuePlayed = cv;
            }
        }
        else {
            int index = 0;

            while (index != p.getCards().size()-1){
                if(p.getCards().get(index).getValue().compareTo(cardValuePlayed) >= 0)
                index++;
            }
        }

    }
}
