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
                Intent scoreIntent=new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(scoreIntent);
            }
        });
        Button play=(Button)findViewById(R.id.button);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent=new Intent(MainActivity.this,PlayActivity.class);
                addName();
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

    }
    private void addName(){
        String name=editTextName.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String scoreId=databaseScore.push().getKey();
            Score score=new Score(scoreId,name,0);
            databaseScore.child(scoreId).setValue(score);
            Toast.makeText(this,"Name add",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
        }
    }

}
