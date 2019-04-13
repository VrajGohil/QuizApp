package io.github.vrajgohil.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button score=(Button)findViewById(R.id.button2);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent=new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(scoreIntent);
            }
        });
        Button play=(Button)findViewById(R.id.button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent(MainActivity.this,PlayActivity.class);
                startActivity(playIntent);
            }
        });
        Button addque=(Button)findViewById(R.id.addque);
        addque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent=new Intent(MainActivity.this,AddQuestion.class);
                startActivity(addIntent);
            }
        });
        Realm.init(this);
    }
}
