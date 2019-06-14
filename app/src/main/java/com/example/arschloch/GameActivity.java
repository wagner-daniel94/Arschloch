package com.example.arschloch;

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
    //Spielerobjkte
    HumanPlayer humanPlayer;
    Player opponentPlayer1;
    Player opponentPlayer2;
    Player opponentPlayer3;
    //Kartenstapelobjekte der ImageViews
    static List<ImageView> handCardsImageViews;
    static List<ImageView> middleCardsImageViews;
    //Textviews für Kartenanzhal Anzeige
    TextView TVacop3;
    TextView TVacop1;
    TextView TVacop2;

    TextView TVCardsPlayedBy;
    //Kartenstapel mit allen Karten
    CardDeck card_deck;
    //Spieler an der Reihe
    int playersTurn;
    //Anzahl Spielzüge
    int turnCount = 1;
    //Anzahl an hintereinander folgenden Skips
    int amountSkipped = 0;
    //Anzahl der Karten die zuvor gespielt wurden
    static int amountCardsPlayed = 0;
    //Kartenwert der zuvor gespielten KArten
    static Card_value cardValuePlayed;
    //Rundenanzahl
    int amountRounds = 1;
    //
    //endregion.

    //Implementation mit Handler
    //Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button passBtn = (Button) findViewById(R.id.passBtn);


        humanPlayer = new HumanPlayer();
        opponentPlayer1 = new OpponentPlayer();
        opponentPlayer2 = new OpponentPlayer();
        opponentPlayer3 = new OpponentPlayer();


        TVacop3 = (TextView)findViewById(R.id.TVacop3) ;
        TVacop1 = (TextView)findViewById(R.id.TVacop1) ;
        TVacop2 = (TextView)findViewById(R.id.TVacop2) ;

        TVCardsPlayedBy = (TextView)findViewById(R.id.TVCardsPlayedBy);

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
        resetRound();

        //Implementation mit Handler
        //handler = new Handler();


    }
@Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.playBtn:
                // Methoden die beim Klick auf Play gestartet werden sollen
                //PlayHumanCards
                try {

                    checkSkipAmount();
                    //play_card gibt einen boolean zurück ob Karten gespielt wurden
                    if(humanPlayer.getCards().size() != 0) {
                        if (humanPlayer.play_card()) {
                            TVCardsPlayedBy.setText("You played:");
                            amountSkipped = 0;
                        }
                        else
                            //Der Spieler kann durch drücken auf Play nicht skippen
                            throw new Exception("Invalid move!");
                    }
                    //Implementation mit Handler
/*
                    handler.post(new Runnable() {
                        public void run() {

                            set_card_imageView();
                            try {
                                Thread.sleep(500);
                            }
                            catch (InterruptedException e){

                            }
                        }
                    });*/
                    set_card_imageView();
                    Thread.sleep(500);

                    checkSkipAmount();
                    if(opponentPlayer1.getCards().size() != 0) {
                        if (opponentPlayer1.play_card()) {
                            amountSkipped = 0;
                            TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                            TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                        } else
                            amountSkipped++;
                    }
                    //Thread.sleep(500);
                    checkSkipAmount();
                    if(opponentPlayer2.getCards().size() != 0) {
                        if (opponentPlayer2.play_card()) {
                            TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                            amountSkipped = 0;
                            TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                        } else
                            amountSkipped++;
                    }
                    //Thread.sleep(500);

                    checkSkipAmount();
                    if(opponentPlayer3.getCards().size() != 0) {
                        if (opponentPlayer3.play_card()) {
                            TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                            amountSkipped = 0;
                            TVacop3.setText(String.valueOf(opponentPlayer2.getCards().size()));
                        } else
                            amountSkipped++;
                    }

                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }
                //PlayAICards
                break;
            case R.id.passBtn:
                // Methoden die beim Klick auf Pass gestartet werden sollen
                //PlayAICards
                try {
                    checkSkipAmount();


                amountSkipped++;
                //Implementation mit runOnUiThread
                /*this.runOnUiThread(new Runnable() {
                    public void run() {
                        set_card_imageView();
                        try {
                            Thread.sleep(5000);
                        }
                        catch (InterruptedException e){

                        }
                    }
                });*/

                    checkSkipAmount();
                if(opponentPlayer1.getCards().size() != 0) {
                    if (opponentPlayer1.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                        amountSkipped = 0;
                        TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                    } else
                        amountSkipped++;
                }

                //Thread.sleep(5000);
                    checkSkipAmount();
                if(opponentPlayer2.getCards().size() !=0) {
                    if (opponentPlayer2.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                        amountSkipped = 0;
                        TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                    } else
                        amountSkipped++;
                }

                //Thread.sleep(5000);
                    checkSkipAmount();
                if(opponentPlayer3.getCards().size() !=0) {
                    if (opponentPlayer3.play_card()) {
                        TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                        amountSkipped = 0;
                        TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                    } else
                        amountSkipped++;
                }
        }
                catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }
                break;
        }
    }

    //check, ob viermal geskippt wurde. setzt dann die Variablen zurück
    private void checkSkipAmount(){
        if (amountSkipped == 4) {
            amountCardsPlayed = 0;
            cardValuePlayed = null;
            amountSkipped = 0;
            set_card_imageView_middleCards(null);
        }
        roundOver();
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

        humanPlayer.getCards().clear();
        opponentPlayer1.getCards().clear();
        opponentPlayer2.getCards().clear();
        opponentPlayer3.getCards().clear();

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
            handCardsImageViews.get(i).invalidate();
        }


    }

    //belegt die ImageViews in der Mitte mit Karten
    public static void set_card_imageView_middleCards(List<Card> combination){

        if(combination == null){
            for (ImageView iv:middleCardsImageViews){
                iv.setVisibility(View.GONE);
            }
            return;
        }

        for(int i = 0;i < middleCardsImageViews.size();i++){

            if(i <= combination.size()-1){
                middleCardsImageViews.get(i).setImageResource(combination.get(i).getResourceId());
                middleCardsImageViews.get(i).setVisibility(View.VISIBLE);

            }
            else
                middleCardsImageViews.get(i).setVisibility(View.GONE);


        }
    }

    //wird bei Klick auf eine Karte ausgelöst: triggert die Methode markCard in humanPlayer
    public void markCard(View v){
        humanPlayer.markCard(v);
        set_card_imageView();
    }
/*
* Festlegung des ersten Spielers
* */
    private int set_firstPlayer(){
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


    private void roundOver(){
        int i = 0;
        int a = 0;
        if(humanPlayer.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                humanPlayer.setWinner(true);
        }
        else{
            a=1;
        }
        if(opponentPlayer1.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer1.setWinner(true);
        }
        else{
            a=2;
        }
        if(opponentPlayer2.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer2.setWinner(true);
        }
        else{
            a=3;
        }
        if(opponentPlayer3.getCards().size() == 0){
            i++;
            if(!checkSomeoneIsWinner())
                opponentPlayer3.setWinner(true);
        }
        else{
            a=4;
        }

        if(i>=3){
            //Runde vorbei
            switch (a){
                case 1:
                    humanPlayer.setArschloch(true);
                    break;
                case 2:
                    opponentPlayer1.setArschloch(true);
                    break;
                case 3:
                    opponentPlayer2.setArschloch(true);
                    break;
                case 4:
                    opponentPlayer3.setArschloch(true);
                    break;
            }
            resetRound();
        }
    }

    private Player getWinner(){
        if(humanPlayer.isWinner())
            return humanPlayer;
        else if(opponentPlayer1.isWinner())
            return opponentPlayer1;
        else if(opponentPlayer2.isWinner())
            return opponentPlayer2;
        else if(opponentPlayer3.isWinner())
            return opponentPlayer3;
        else
            return null;
    }

    private Player getArschloch(){
        if(humanPlayer.isArschloch())
            return humanPlayer;
        else if(opponentPlayer1.isArschloch())
            return opponentPlayer1;
        else if(opponentPlayer2.isArschloch())
            return opponentPlayer2;
        else if(opponentPlayer3.isArschloch())
            return opponentPlayer3;
        else
            return null;
    }

    private boolean checkSomeoneIsWinner(){
        if(humanPlayer.isWinner())
            return true;
        else if(opponentPlayer1.isWinner())
            return true;
        else if(opponentPlayer2.isWinner())
            return true;
        else if(opponentPlayer3.isWinner())
            return true;
        else
            return false;
    }

    //Nach Ende der Runde wird alles zurückgesetzt. Wird auch vor der 1. Runde aufgerufen
    private void resetRound(){
        cards_distributing();
        set_card_imageView();
        set_card_imageView_middleCards(null);
        amountSkipped = 0;
        amountCardsPlayed = 0;
        cardValuePlayed = null;
        playersTurn = set_firstPlayer();

        if(amountRounds != 1){
            //Drücken
            if(getWinner() == humanPlayer){

            }

        }

        humanPlayer.setArschloch(false);
        humanPlayer.setWinner(false);
        opponentPlayer1.setArschloch(false);
        opponentPlayer1.setWinner(false);
        opponentPlayer2.setArschloch(false);
        opponentPlayer2.setWinner(false);
        opponentPlayer3.setArschloch(false);
        opponentPlayer3.setWinner(false);
        TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
        TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
        TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
        TVCardsPlayedBy.setText("");

        //Je nachdem wer zuerst dran ist

        //Wenn Opponent Player 1 zuerst dran ist
        if(playersTurn == 2) {
            Toast.makeText(this,"Opponent Player 1 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer1.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer1 played:");
                    amountSkipped = 0;
                    TVacop1.setText(String.valueOf(opponentPlayer1.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(5000);
                if (opponentPlayer2.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                    amountSkipped = 0;
                    TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(500);
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        //Wenn Opponent Player 2 zuerst dran ist
        else if(playersTurn == 3){
            Toast.makeText(this,"Opponent Player 2 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer2.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer2 played:");
                    amountSkipped = 0;
                    TVacop2.setText(String.valueOf(opponentPlayer2.getCards().size()));
                } else
                    amountSkipped++;
                //Thread.sleep(500);
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        //Wenn Opponent Player 3 zuerst dran ist
        else if(playersTurn == 4){
            Toast.makeText(this,"Opponent Player 3 begins",Toast.LENGTH_LONG).show();
            try {
                if (opponentPlayer3.play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer3 played:");
                    amountSkipped = 0;
                    TVacop3.setText(String.valueOf(opponentPlayer3.getCards().size()));
                } else
                    amountSkipped++;
            }
            catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }


}
