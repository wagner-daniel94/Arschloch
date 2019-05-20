package com.example.arschloch;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //

    //region Variablen
    HumanPlayer humanPlayer;

    Player opponentPlayer1;
    Player opponentPlayer2;
    Player opponentPlayer3;
    static List<ImageView> handCardsImageViews;
    static List<ImageView> middleCardsImageViews;


    //Karten

    CardDeck card_deck;


    //Spieler an der

    int playersTurn;
    int turnCount = 1;


    //Anzahl der Karten die zuvor gespielt wurden

    static int amountCardsPlayed = 0;


    //Kartenwert der zuvor gespielten KArten

    static Card_value cardValuePlayed;

    //endregion



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button passBtn = (Button) findViewById(R.id.passBtn);

        playBtn.setOnClickListener(this);
        passBtn.setOnClickListener(this);

        handCardsImageViews = new ArrayList<>();
        middleCardsImageViews = new ArrayList<>();

        handCardsImageViews.add((ImageView)findViewById(R.id.card1));
        handCardsImageViews.add((ImageView)findViewById(R.id.card2));
        handCardsImageViews.add((ImageView)findViewById(R.id.card3));
        handCardsImageViews.add((ImageView)findViewById(R.id.card4));
        handCardsImageViews.add((ImageView)findViewById(R.id.card5));
        handCardsImageViews.add((ImageView)findViewById(R.id.card6));
        handCardsImageViews.add((ImageView)findViewById(R.id.card7));
        handCardsImageViews.add((ImageView)findViewById(R.id.card8));
        handCardsImageViews.add((ImageView)findViewById(R.id.card9));
        handCardsImageViews.add((ImageView)findViewById(R.id.card10));
        handCardsImageViews.add((ImageView)findViewById(R.id.card11));
        handCardsImageViews.add((ImageView)findViewById(R.id.card12));
        handCardsImageViews.add((ImageView)findViewById(R.id.card13));

        middleCardsImageViews.add((ImageView)findViewById(R.id.middlecard1));
        middleCardsImageViews.add((ImageView)findViewById(R.id.middlecard2));
        middleCardsImageViews.add((ImageView)findViewById(R.id.middlecard3));
        middleCardsImageViews.add((ImageView)findViewById(R.id.middlecard4));
        cards_distributing();
        set_card_imageView();
        set_card_imageView_middleCards();
    }
@Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.playBtn:
                // Methoden die beim Klick auf Play gestartet werden sollen
                //PlayHumanCards
                try {
                    humanPlayer.play_card();
                    set_card_imageView();

                    Thread.sleep(5000);
                    opponentPlayer1.play_card();
                    Thread.sleep(5000);
                    opponentPlayer2.play_card();
                    Thread.sleep(5000);
                    opponentPlayer3.play_card();
                }
                catch (Exception e){
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }
                //PlayAICards
                break;
            case R.id.passBtn:
                // Methoden die beim Klick auf Pass gestartet werden sollen
                //PlayAICards
                break;
        }
    }


/*
* Das austeielen der Karten
* */
    private void cards_distributing(){
        //Zähler für Spieler 1-4 (0-3)
        int i = 0;
        //Zähler für ausgeteilte Karten
        int c = 0;
        //Variable um eine Karte zu erstellen
        card_deck = new CardDeck();

        humanPlayer = new HumanPlayer();
        opponentPlayer1 = new OpponentPlayer();
        opponentPlayer2 = new OpponentPlayer();
        opponentPlayer3 = new OpponentPlayer();

        Collections.shuffle(card_deck.getCards());

        /* Karten erzeugen
        for(int k= 0;k<=12;k++){
            for(int l = 0;l<=3;l++){
                create_card = new Card(Card_value.values()[k], Card_symbol.values()[l]);//weil sich der Konstruktor geändert hat
                card_deck.add(create_card);
            }
        }
        */

        while(true)
        {
                switch (i) {
                    case 0:
                        humanPlayer.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 1:
                        opponentPlayer1.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 2:
                        opponentPlayer2.getCards().add(card_deck.getCards().get(c));
                        break;
                    case 3:
                        opponentPlayer3.getCards().add(card_deck.getCards().get(c));
                       break;
                }
                if(c!=card_deck.getCards().size()-1)
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
        Collections.sort(humanPlayer.getCards());
        Collections.sort(opponentPlayer1.getCards());
        Collections.sort(opponentPlayer2.getCards());
        Collections.sort(opponentPlayer3.getCards());
        set_card_imageView();

    }

    /*
    Karte einem ImageView zur Laufzeit zuweisen
     */
    public void set_card_imageView(){

        for(int i = 0; i < handCardsImageViews.size();i++){
            if(i<= humanPlayer.getCards().size()-1) {

                handCardsImageViews.get(i).setVisibility(View.VISIBLE);
                handCardsImageViews.get(i).setImageResource(humanPlayer.getCards().get(i).getResourceId());
                handCardsImageViews.get(i).setTag(humanPlayer.getCards().get(i).getResourceId());
                if (humanPlayer.getCards().get(i).isMarked())
                {
                    handCardsImageViews.get(i).setForeground(getDrawable(R.drawable.marked));
                }
                else
                {
                    handCardsImageViews.get(i).setForeground(null);
                }
            }
            else {
                handCardsImageViews.get(i).setVisibility(View.GONE);
            }
        }


    }

    public void set_card_imageView_middleCards(){
        for(ImageView iv:middleCardsImageViews)
        {
            iv.setVisibility(View.GONE);
        }
    }

    public void markCard(View v){
        humanPlayer.markCard(v);
        set_card_imageView();
    }
/*
* Festlegung des ersten Spielers
* */
    private int get_firstPlayer(){
        //Wenn Spieler Arschloch in der Runde zuvor war beginnt er das Spiel. Wenn es die erste Runde ist entscheidet der Zufall
        if(humanPlayer.isArschloch()){
            return 1;}
        else if(opponentPlayer1.isArschloch())
            return  2;
        else if(opponentPlayer2.isArschloch())
            return  3;
        else if(opponentPlayer3.isArschloch())
            return  4;
        else
            return (int)(Math.random()*4)+1;
    }

    private Player get_winner(){
        int points =0;
        if(humanPlayer.isWinner()){
            points = humanPlayer.getPoints();
            points++;
            humanPlayer.setPoints(points);
            return humanPlayer;}
        else if(opponentPlayer1.isWinner()){
            points = opponentPlayer1.getPoints();
            points++;
            opponentPlayer1.setPoints(points);
            return opponentPlayer1;}
        else if(opponentPlayer2.isWinner()){
            points = opponentPlayer2.getPoints();
            points++;
            opponentPlayer2.setPoints(points);
            return opponentPlayer2;}
        else if(opponentPlayer3.isWinner()){
            points = opponentPlayer3.getPoints();
            points++;
            opponentPlayer3.setPoints(points);
            return opponentPlayer3;}
        else
        return null;
    }

    //Zählen von Zügen
    private int turnCount (){
        if (playersTurn==0) {
            turnCount++;}
            return turnCount;

    }
    //Zählen des Spielers der an der Reihe ist
    private int playerPlayer () {
        if (playersTurn == 4)

            return 1;
        else
            return playersTurn++;

    }


}
