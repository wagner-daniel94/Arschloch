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

        Button startGameBtn = findViewById(R.id.startGameBtn);
        Button openStatsBtn = findViewById(R.id.openStatsBtn);
        Button openIntroductionBtn = findViewById(R.id.openIntroductionBtn);

        startGameBtn.setOnClickListener(this);
        openStatsBtn.setOnClickListener(this);
        openIntroductionBtn.setOnClickListener(this);
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultStatisticsIfNeed();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.openStatsBtn:
                openActivity(StatisticActivity.class);
                break;
            case R.id.startGameBtn:
                openActivity(GameActivity.class);
                break;
            case R.id.openIntroductionBtn:
                openActivity(IntroductionActivity.class);
                break;
        }
    }

    public void openActivity(Class c){
        Intent intent = new Intent (this, c);
        startActivity(intent);
    }
}
