package io.github.vrajgohil.quizapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuestion extends AppCompatActivity {
    EditText addQuestion;
    EditText addAnswer;
    EditText addOption1;
    EditText addOption2;
    EditText addOption3;
    EditText addOption4;
    Button addButton;
    String q_id;
    DatabaseReference questionDatabase;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        addQuestion=findViewById(R.id.addquestion);
        addAnswer=findViewById(R.id.addanswer);
        addOption1=findViewById(R.id.addoption1);
        addOption2=findViewById(R.id.addoption2);
        addOption3=findViewById(R.id.addoption3);
        addOption4=findViewById(R.id.addoption4);
        addButton=findViewById(R.id.addbtn);
        questionDatabase=FirebaseDatabase.getInstance().getReference("questions");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestions();
            }
        });

    }
    private void addQuestions(){
        String question=addQuestion.getText().toString().trim();
        String answer=addAnswer.getText().toString().trim();
        String option1=addOption1.getText().toString().trim();
        String option2=addOption2.getText().toString().trim();
        String option3=addOption3.getText().toString().trim();
        String option4=addOption4.getText().toString().trim();
        if(!TextUtils.isEmpty(question) && !TextUtils.isEmpty(answer) && Integer.parseInt(answer)>=1 && Integer.parseInt(answer)<=4 && !TextUtils.isEmpty(option1) && !TextUtils.isEmpty(option2) && !TextUtils.isEmpty(option3) && !TextUtils.isEmpty(option4)){
            q_id=questionDatabase.push().getKey();
            Questions Q=new Questions(q_id,question,option1,option2,option3,option4,answer,String.valueOf(0));
            questionDatabase.child(q_id).setValue(Q);
            Toast.makeText(this,"Question Added!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Check your entries!",Toast.LENGTH_LONG).show();
        }

    }
}
