package com.example.arschloch;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.arschloch.R.id.IVop1;
import static com.example.arschloch.R.id.IVop2;
import static com.example.arschloch.R.id.IVop3;
import static com.example.arschloch.R.id.card1;
import static com.example.arschloch.R.id.handCardLayout;


/*
Änderung: Austeilalgorithmus
Datum: 21.04.2019
Autor: Wagner
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    //

    //region Variablen
    //Spielerobjkte
    List<Player> allPlayer;

    //Kartenstapelobjekte der ImageViews
    static List<ImageView> handCardsImageViews;
    static List<ImageView> middleCardsImageViews;
    //Textviews für Kartenanzhal Anzeige
    List<TextView> TVacop;


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
    int amountRounds = 0;
    //Rundenanzahl für die Statistik
    int amountRoundsStatistic = 0;
    //gewonnene Runden
    int amountRoundsWon = 0;
    // anzahl gespielter Runden
    int amountTurnsPlayed = 0;
    //Anzahl verlorener Spiele
    int amountGamesLost = 0;
    ObjectAnimator animY,animX;
    MediaPlayer mp;
    ConstraintLayout gameLayout;
    ConstraintLayout wishLayout;
    Spinner spinner;
    Button IwantCardBtn;
    Button IwantNoCardBtn;
    //endregion.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button passBtn = (Button) findViewById(R.id.passBtn);
        gameLayout =findViewById(R.id.gameLayout);
        wishLayout =findViewById(R.id.wishLayout);
        spinner = findViewById(R.id.spinner);
        IwantCardBtn = findViewById(R.id.IwantCardBtn);
        IwantNoCardBtn = findViewById(R.id.IwantNoCardBtn);
        card_deck = new CardDeck();
        allPlayer = new ArrayList<>();
        TVacop = new ArrayList<>();
        int[] array = new int[]{R.id.TVacop1,R.id.TVacop2,R.id.TVacop3};
        allPlayer.add(new HumanPlayer());
        for (int i = 0; i < 3; i++) {
            allPlayer.add(new OpponentPlayer());
            TVacop.add((TextView)findViewById(array[i]));
        }

        TVCardsPlayedBy = (TextView)findViewById(R.id.TVCardsPlayedBy);

        playBtn.setOnClickListener(this);
        passBtn.setOnClickListener(this);

        mp = MediaPlayer.create(this,R.raw.arschloch);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                       @Override
                                       public void onCompletion(MediaPlayer mediaPlayer) {
                                           mp.reset();
                                           mp =MediaPlayer.create(GameActivity.this,R.raw.arschloch);
                                       }
                                   });

        handCardsImageViews = new ArrayList<>();
        middleCardsImageViews = new ArrayList<>();

        //Zuweisung der Karten-IDs
        array = new int[]{card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8, R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13};

        for (int i = 0; i < array.length; i++) {
            handCardsImageViews.add((ImageView)findViewById(array[i]));
        }
        array = new int[]{R.id.middlecard1,R.id.middlecard2,R.id.middlecard3,R.id.middlecard4};

        for (int i = 0; i < array.length; i++) {
            middleCardsImageViews.add((ImageView)findViewById(array[i]));
        }

        List<Card> combinations = (List<Card>) Player.getHelperCombination();

        //View view = (View) findViewById(R.id.middlecard1);
       //float x = view.getX();
        // float y = view.getY();
       // int peng =combinations.get(0).getResourceId();
        //ImageView animCard = (ImageView) findViewById(card1) ;
       // ObjectAnimator translateAnimationX = ObjectAnimator.ofFloat(animCard, View.TRANSLATION_X, 800);
       // ObjectAnimator translateAnimationY = ObjectAnimator.ofFloat(animCard, View.TRANSLATION_Y, 800);

        //PropertyValuesHolder tax = PropertyValuesHolder.ofFloat(View.TRANSLATION_X,800);
        //PropertyValuesHolder tay = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y,800);



      //  moveAnimation.setRepeatCount(1);
       // moveAnimation.setRepeatMode(ValueAnimator.REVERSE);

//        setupAnimation.playTogether(translateAnimationX,translateAnimationY);
        //setupAnimation.play(translateAnimationX);
  //      setupAnimation.setDuration(2000);
    //    setupAnimation(animCard,translateAnimationX,R.animator.move);

        resetGame();


    }
@Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.playBtn:
                // Methoden die beim Klick auf Play gestartet werden sollen
                // PlayHumanCards
                try {
                    resetRound(getAmountPlayersInGame());
                    if(checkGameOver())
                        return;
                    //play_card gibt einen boolean zurück ob Karten gespielt wurden
                    if(allPlayer.get(0).getCards().size() != 0) {
                        if (allPlayer.get(0).play_card()) {
                            TVCardsPlayedBy.setText("You played:");
                            amountSkipped = 0;
                            amountTurnsPlayed++;
                        }
                        else
                            //Der Spieler kann durch drücken auf Play nicht skippen
                            throw new Exception("Invalid move!");
                    }

                    showPlayersCards();
                    Thread.sleep(500);

                    for(int i = 1;i<allPlayer.size();i++){
                        resetRound(getAmountPlayersInGame());
                        if(checkGameOver())
                            return;

                        if(allPlayer.get(i).getCards().size() != 0) {
                            if (allPlayer.get(i).play_card()) {
                                amountSkipped = 0;
                                TVCardsPlayedBy.setText("OpponentPlayer " + i + " played:");
                                if (allPlayer.get(i) == allPlayer.get(1)){
                                    ImageView animCard = findViewById(IVop1);
                                    enemyAnimation(animCard);
                                }else if(allPlayer.get(i)==allPlayer.get(2)){
                                    ImageView animCard = findViewById(IVop2);
                                    enemyAnimation(animCard);
                                }else if(allPlayer.get(i)==allPlayer.get(3)){
                                    ImageView animCard = findViewById(IVop3);
                                    enemyAnimation(animCard);
                                }

                                TVacop.get(i-1).setText(String.valueOf(allPlayer.get(i).getCards().size()));
                            } else
                                amountSkipped++;
                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }
                //PlayAICards
              /*
                Button playBtn = (Button) findViewById(R.id.playBtn);
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.1f);
                ObjectAnimator scaleAnimation =
                        ObjectAnimator.ofPropertyValuesHolder(playBtn, pvhX, pvhY);
                scaleAnimation.setRepeatCount(0);
                scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

                AnimatorSet setupAnimation = new AnimatorSet();
                setupAnimation.play(scaleAnimation);
                setupAnimation(playBtn,scaleAnimation,R.animator.scale);
                */
              break;
            case R.id.passBtn:
                // Methoden die beim Klick auf Pass gestartet werden sollen
                //PlayAICards
                try {
                    resetRound(getAmountPlayersInGame());
                    if(checkGameOver())
                        return;

                //Player hat geskippt
                amountSkipped++;
                amountTurnsPlayed++;
                    for(int i = 1;i<allPlayer.size();i++){
                        resetRound(getAmountPlayersInGame());
                        if(checkGameOver())
                            return;

                        if(allPlayer.get(i).getCards().size() != 0) {
                            if (allPlayer.get(i).play_card()) {
                                amountSkipped = 0;
                                TVCardsPlayedBy.setText("OpponentPlayer " + i + " played:");
                                TVacop.get(i-1).setText(String.valueOf(allPlayer.get(i).getCards().size()));
                                if (allPlayer.get(i) == allPlayer.get(1)){
                                    ImageView animCard = findViewById(IVop1);
                                    enemyAnimation(animCard);
                                }else if(allPlayer.get(i)==allPlayer.get(2)){
                                    ImageView animCard = findViewById(IVop2);
                                    enemyAnimation(animCard);
                                }else if(allPlayer.get(i)==allPlayer.get(3)){
                                    ImageView animCard = findViewById(IVop3);
                                    enemyAnimation(animCard);
                                }

                            } else
                                amountSkipped++;
                        }
                    }
                 }
                catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
                }
                break;
            case R.id.IwantCardBtn:
                 //Button vom wishLayout
                String item = (String) spinner.getSelectedItem();
                Card_value cv = Card_value.valueOf(item);
                allPlayer.get(0).wuenschen(getArschloch(),cv);
                gameLayout.setVisibility(View.VISIBLE);
                wishLayout.setVisibility(View.GONE);
                break;
            case R.id.IwantNoCardBtn:
                //Button vom wishLayout
                gameLayout.setVisibility(View.VISIBLE);
                wishLayout.setVisibility(View.GONE);
                break;
        }

        System.out.println("Log: Before Game Loop");
        if(allPlayer.get(0).getCards().size() == 0) {
            finishing_Gameloop();
        }
    }

    //check, wie viele Spieler noch in game sind
    private int getAmountPlayersInGame(){

        int playersInGame = 0;
        for(int i = 0;i < allPlayer.size();i++) {
            if (allPlayer.get(i).getCards().size() > 0)
                playersInGame++;
        }
         return playersInGame;
    }

    //check, ob viermal geskippt wurde. setzt dann die Variablen zurück
    private void resetRound(int i){
        // -1 da immer "Anzahl Spieler - 1" Skips notwendig sind um eine Runde zu beenden
        if (amountSkipped >= i - 1) {
            amountCardsPlayed = 0;
            cardValuePlayed = null;
            amountSkipped = 0;
            resetMiddleCard();
        }
    }

/*
* Das austeielen der Karten
* */
    private void cards_distributing(){
        //Zähler für Spieler 1-4 (0-3)
        int s = 0;
        //Zähler für ausgeteilte Karten
        int c = 0;
        //Variable um eine Karte zu erstellen

        for(int i = 0;i<allPlayer.size();i++)
            allPlayer.get(i).getCards().clear();

        Collections.shuffle(card_deck.getCards());

        while(c < card_deck.getCards().size())
        {
                allPlayer.get(s).getCards().add(card_deck.getCards().get(c));
                //Zähler hochzählen
                c++;
                //Auswahl des nächsten Spielers, der eine Karte erhält. Nach Spieler4(i==3) ist Spieler1 wieder an der Reihe
                if(s==3)
                    s=0;
                else
                    s++;
            }

        //Sortieren der Kartendecks
        for(int i = 0;i<allPlayer.size();i++)
        Collections.sort(allPlayer.get(i).getCards());

        showPlayersCards();

    }

    /*
    Karte einem ImageView zur Laufzeit zuweisen
     */
    public void showPlayersCards(){

        for(int i = 0; i < handCardsImageViews.size();i++){
            if(i<= allPlayer.get(0).getCards().size()-1) {

                handCardsImageViews.get(i).setVisibility(View.VISIBLE);
                handCardsImageViews.get(i).setImageResource(allPlayer.get(0).getCards().get(i).getResourceId());
                handCardsImageViews.get(i).setTag(allPlayer.get(0).getCards().get(i).getResourceId());
                if (allPlayer.get(0).getCards().get(i).isMarked())
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


    //Setzt alle ImageViews in der Mitte zurück
    public void resetMiddleCard(){
        for (ImageView iv:middleCardsImageViews)
            iv.setVisibility(View.GONE);
    }

    //belegt die ImageViews in der Mitte mit Karten
    public static void showMiddleCards(List<Card> combination){
            for(int i = 0;i < middleCardsImageViews.size();i++) {
                if (i <= combination.size() - 1) {
                    middleCardsImageViews.get(i).setImageResource(combination.get(i).getResourceId());
                    //move(middleCardsImageViews.get(i));
                    middleCardsImageViews.get(i).setVisibility(View.VISIBLE);
                } else
                    middleCardsImageViews.get(i).setVisibility(View.GONE);
            }
    }

    //wird bei Klick auf eine Karte ausgelöst: triggert die Methode markCard in humanPlayer
    public void markCard(View v){
        allPlayer.get(0).markCard(v);
        showPlayersCards();
    }

    /*
    * Festlegung des ersten Spielers
    * Wenn Spieler Arschloch in der Runde zuvor war beginnt er das Spiel. Wenn es die erste Runde ist entscheidet der Zufall
    */
    private int set_firstPlayer(){

        for(int i = 0;i<allPlayer.size();i++) {
            if (allPlayer.get(i).isArschloch()) {
                return i+1;
            }
        }

        return (int)(Math.random()*4)+1;
    }

    /*
     *Überprüft ob das Spiel vorbei ist und setzt Gewinner & Arschloch
     */
    private boolean checkGameOver(){
        int s = 0;
        int a = 0;
        for (int i = 0;i<allPlayer.size();i++) {
            if (allPlayer.get(i).getCards().size() == 0) {
                s++;
                if (!checkSomeoneIsWinner())
                    allPlayer.get(i).setWinner(true);
                    if(allPlayer.get(0).isWinner()){
                        amountRoundsWon++;
                    }
            } else {
                a = i;
            }
        }

        if(s>=3){
            //Runde vorbei
            if(a==0){
                //Spieler ist Arschloch
                Toast.makeText(this,"Du bist Arschloch!!",Toast.LENGTH_LONG).show();
                amountGamesLost++;
                mp.start();
            }
            allPlayer.get(a).setArschloch(true);
            resetGame();
            return true;
        }
        return false;
    }
    /*
     * Überprüft ob es bereits einen Gewinner gibt,
     * damit kein weiterer Gewinner gesetzt wird
     */
    private boolean checkSomeoneIsWinner(){
        for (int i = 0;i<allPlayer.size();i++) {
            if (allPlayer.get(i).isWinner())
                return true;
        }
        return false;
    }

    //Nach Ende der Runde wird alles zurückgesetzt. Wird auch vor der 1. Runde aufgerufen
    private void resetGame(){
        amountRounds++;
        updateStatistics();
        amountRoundsStatistic++;
        demarkDeck();
        cards_distributing();
        showPlayersCards();
        resetMiddleCard();
        amountSkipped = 0;
        amountCardsPlayed = 0;
        cardValuePlayed = null;
        playersTurn = set_firstPlayer();

        if(amountRounds > 1){
            //Drücken
           if(getWinner() instanceof HumanPlayer) {
               gameLayout.setVisibility(View.GONE);
               wishLayout.setVisibility(View.VISIBLE);

               IwantCardBtn.setOnClickListener(this);
               IwantNoCardBtn.setOnClickListener(this);

            }
           else {
               getWinner().wuenschen(getArschloch(), null);
           }
           }

        //Test




        //Arschloch und Winner zurücksetzen
        for (int i = 0;i<allPlayer.size();i++){
            allPlayer.get(i).setArschloch(false);
            allPlayer.get(i).setWinner(false);
        }

        //TextView für die Anzahl der Karten wird zurückgesetzt
        for (int i = 1; i < allPlayer.size(); i++) {
            TVacop.get(i-1).setText(String.valueOf(allPlayer.get(i).getCards().size()));
        }

        TVCardsPlayedBy.setText("");

        //Je nachdem wer zuerst dran ist
        if(playersTurn != 1) {
          play_First_Opponent_Round();
        }
    }

    private void play_First_Opponent_Round(){
        Toast.makeText(this, "OpponentPlayer" + (playersTurn - 1) + " begins", Toast.LENGTH_LONG).show();
        try {
            opponentPlayerPlayCard(playersTurn - 1);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    //Entfernt alle Markierungen nach einer komplett gespielteen Runde
    private void demarkDeck(){
        for (Card c: card_deck.getCards()){
            c.setMarked(false);
        }
    }

    //Methode die dafür sorgt, dass die Runde zuende gespielt wird wenn humanPlayer keine Karten mehr hat
    private void finishing_Gameloop(){

            while(getAmountPlayersInGame() > 1){

                System.out.println("Log: Finishing Game Loop Initiated");
                try {
                    opponentPlayerPlayCard(1);
                }catch (Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                resetRound(getAmountPlayersInGame());
            }
            resetGame();
    }

    /*
     * Wird aufgerufen wenn:
     *  1. Wenn ein Gegner das Spiel beginnt
     *  2. Wenn nurnoch Gegner im Spiel sind
     *  -> Nur Gegner spielen Karten
     */
    private void opponentPlayerPlayCard(int startPlayer) throws Exception{
        for (int i = startPlayer; i < allPlayer.size(); i++) {
            if (allPlayer.get(i).getCards().size() != 0) {
                if (allPlayer.get(i).play_card()) {
                    TVCardsPlayedBy.setText("OpponentPlayer" + i + " played:");
                    amountSkipped = 0;
                    TVacop.get(i-1).setText(String.valueOf(allPlayer.get(i).getCards().size()));
                } else
                    amountSkipped++;
            }
        }
    }

    private Player getWinner(){

        for (int i = 0; i < allPlayer.size(); i++) {
            if(allPlayer.get(i).isWinner())
                return allPlayer.get(i);
        }
        return null;
    }

    private Player getArschloch(){
        for (int i = 0; i < allPlayer.size(); i++) {
            if(allPlayer.get(i).isArschloch())
                return allPlayer.get(i);
        }
        return null;
    }

    private void updateStatistics(){

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        for (int j = 1; j<6;j++) {
            Statistic statistics = db.getStatistic(j);
            int zwischenspeicher = Integer.parseInt(statistics.getStatisticNumber());

            if (j == 1){
                zwischenspeicher=zwischenspeicher+amountRoundsStatistic;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 2){
                zwischenspeicher=zwischenspeicher+amountRoundsWon;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 3){
                int winPercentage = Integer.parseInt(statistics.getStatisticNumber());
                Statistic help= db.getStatistic(1);
                int helpAmountTurns = Integer.parseInt(help.getStatisticNumber());
                Statistic help2= db.getStatistic(2);
                int helpWonTurns = Integer.parseInt(help.getStatisticNumber());
                if(helpAmountTurns == 0){
                    helpAmountTurns = 1;
                }else {
                    winPercentage = helpWonTurns/helpAmountTurns+1;
                }
                statistics.setStatisticNumber(String.valueOf(winPercentage));
                db.updateStatistics(statistics);
            }else if (j == 4){
                zwischenspeicher=zwischenspeicher+amountTurnsPlayed;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }else if (j == 5){
                zwischenspeicher=zwischenspeicher+amountGamesLost;
                statistics.setStatisticNumber(String.valueOf(zwischenspeicher));
                db.updateStatistics(statistics);
            }

        }
    }
  /*  public void move(View view){
        ObjectAnimator animX,animY;
        View helper = (View) findViewById(R.id.middlecard1);
        float x = helper.getX();
        float y = helper.getY();

        animX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, x);
        animY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, y);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX,animY);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
*/
    private void setupAnimation(View view, final Animator animation, final int animationID) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    Animator anim = AnimatorInflater.loadAnimator(GameActivity.this, animationID);
                    anim.setTarget(v);
                    anim.start();
                    animation.start();


           }
        });
    }
    private void enemyAnimation (View view){

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.1f);
        ObjectAnimator scaleAnimation =
                ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);

        int animationID = R.animator.scale;

        AnimatorSet setupAnimation = new AnimatorSet();
        setupAnimation.play(scaleAnimation);
        setupAnimation(view,scaleAnimation,R.animator.scale);
        Animator anim = (Animator) AnimatorInflater.loadAnimator(GameActivity.this,animationID);
        anim.setTarget(view);
        anim.start();
        scaleAnimation.start();
    }


    //Animation muss in ONCreate kreiert werden
    //xml files müssen noch erweitert werden bzw die Animation irgendwie Fix - evtl hardcoded Abstand zwischen hand Cards und Middlecards
    //Testen ob das mit der combination Liste funktioniert
    //Umbenennen von Anzahl Züge zu anzahl Züge letztes spiel
    //Scheiß auf bewegungs animation. Skalliere es einfach statt die Karte zu markieren, dann ist das schöner und die gleiche Animation kann genutzt werden um den gegnern beim SPielen einer karte eine Animation zu zu weisen.


}