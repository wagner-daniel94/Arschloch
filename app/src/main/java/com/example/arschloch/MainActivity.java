package com.example.arschloch;

/*
Änderung:
Datum: 17.04.2019   / 06.05.2019
Autor: Wagner       / Schoellhorn
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startGameBtn = (Button) findViewById(R.id.startGameBtn);
        Button openStatsBtn = (Button) findViewById(R.id.openStatsBtn);

        startGameBtn.setOnClickListener(this);
        openStatsBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.openStatsBtn:
                    openActivityStats();
                    break;
            case R.id.startGameBtn:
                    openActivityGame();
                    break;
        }
    }

    public void openActivityStats(){
        Intent intent = new Intent (this, Statistic.class);
        startActivity(intent);
    }
    public void openActivityGame(){
        Intent intent = new Intent (this, GameActivity.class);
        startActivity(intent);
    }
}
