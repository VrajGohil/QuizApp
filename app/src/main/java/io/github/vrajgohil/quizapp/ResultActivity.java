package io.github.vrajgohil.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView TotalValue;
    TextView CorrectValue;
    TextView WrongValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TotalValue=findViewById(R.id.textViewTotalValue);
        CorrectValue=findViewById(R.id.textViewCorrectValue);
        WrongValue=findViewById(R.id.textViewWrongValue);

        Intent intent=getIntent();
        String questions=intent.getStringExtra("total");
        String correct=intent.getStringExtra("correct");
        String wrong=intent.getStringExtra("wrong");
        TotalValue.setText(questions);
        CorrectValue.setText(correct);
        WrongValue.setText(wrong);
    }
}
