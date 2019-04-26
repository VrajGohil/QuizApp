package io.github.vrajgohil.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView TotalValue;
    TextView CorrectValue;
    TextView WrongValue;
    Button buttonPlayAgain;
    Button buttonScoreboard;
    Intent MainIntent;
    Intent ScoreIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TotalValue=findViewById(R.id.textViewTotalValue);
        CorrectValue=findViewById(R.id.textViewCorrectValue);
        WrongValue=findViewById(R.id.textViewWrongValue);

        buttonPlayAgain=findViewById(R.id.buttonPlayAgain);
        buttonScoreboard=findViewById(R.id.buttonToScores);

        Intent intent=getIntent();
        String questions=intent.getStringExtra("total");
        String correct=intent.getStringExtra("correct");
        String wrong=intent.getStringExtra("wrong");
        TotalValue.setText(questions);
        CorrectValue.setText(correct);
        WrongValue.setText(wrong);

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
