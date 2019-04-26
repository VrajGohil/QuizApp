package io.github.vrajgohil.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {
    TextView CorrectValue;
    Button buttonPlayAgain;
    Button buttonScoreboard;
    Intent MainIntent;
    Intent ScoreIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        CorrectValue=findViewById(R.id.textViewCorrectValue);
        buttonPlayAgain=findViewById(R.id.buttonPlayAgain);
        buttonScoreboard=findViewById(R.id.buttonToScores);

        Intent intent=getIntent();
        String correct=intent.getStringExtra("correct");
        CorrectValue.setText(correct);

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainIntent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(MainIntent);
            }
        });
        buttonScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreIntent=new Intent(ResultActivity.this,ScoreView.class);
                startActivity(ScoreIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent=new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
