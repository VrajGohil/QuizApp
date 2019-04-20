package io.github.vrajgohil.quizapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Questions> questionList;
    private TextView questionView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mDatabase = FirebaseDatabase.getInstance().getReference("questions");
        questionList = new ArrayList<>();
        questionView = findViewById(R.id.questionView);
        questionView.setText(questionList.get(0).getQuestion());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionList.clear();
                for(DataSnapshot questionSnapshot :dataSnapshot.getChildren()){
                    Questions q=questionSnapshot.getValue(Questions.class);
                    questionList.add(q);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "loadPost:onCancelled", databaseError.toException());
                Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
