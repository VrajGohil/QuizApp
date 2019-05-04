package io.github.vrajgohil.quizapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreView extends AppCompatActivity {
    ListView listViewScores;
    DatabaseReference reference;
    List<Score> scoreList;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_view);
        listViewScores= findViewById(R.id.listViewScores);
        reference=FirebaseDatabase.getInstance().getReference().child("scores");
        scoreList=new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Scores Loading");
        progressDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug","I am here");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scoreList.clear();
                for(DataSnapshot scoreSnapshot : dataSnapshot.getChildren()){
                    Score score = scoreSnapshot.getValue(Score.class);
                    scoreList.add(score);
                }
                Log.d("Debug",scoreList.get(3).getScoreName());
                Collections.sort(scoreList, (o2, o1) -> Integer.compare(Integer.parseInt(o1.scoreValue),Integer.parseInt(o2.scoreValue)));
                progressDialog.dismiss();
                ScoreList adaptor = new ScoreList(ScoreView.this,scoreList);
                listViewScores.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Database Error",databaseError.toException());
            }
        });
    }
}
