package io.github.vrajgohil.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity{
    EditText editTextName;
    DatabaseReference databaseScore;
    Intent playIntent;
    String scoreId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName=(EditText)findViewById(R.id.editTextName);
        databaseScore=FirebaseDatabase.getInstance().getReference("scores");

        Button score=(Button)findViewById(R.id.button2);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent=new Intent(MainActivity.this,ScoreView.class);
                startActivity(scoreIntent);
            }
        });
        Button play=(Button)findViewById(R.id.button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playIntent=new Intent(MainActivity.this,PlayActivity.class);
                addName();
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

    }
    private void addName(){
        String name=editTextName.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            scoreId=databaseScore.push().getKey();
            Score score=new Score(scoreId,name,0);
            databaseScore.child(scoreId).setValue(score);
            Toast.makeText(this,"Name Added Successfully!",Toast.LENGTH_SHORT).show();
            startActivity(playIntent);
        }
        else{
            Toast.makeText(this,"Enter name to play!",Toast.LENGTH_LONG).show();
        }
    }


}
