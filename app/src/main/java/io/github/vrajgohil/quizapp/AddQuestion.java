package io.github.vrajgohil.quizapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddQuestion extends AppCompatActivity {

    EditText question,option1,option2,option3,option4,ans;
    Realm realm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        question = (EditText)findViewById(R.id.addquestion);
        ans = (EditText)findViewById(R.id.addanswer);
        option1= (EditText)findViewById(R.id.addoption1);
        option2= (EditText)findViewById(R.id.addoption2);
        option3= (EditText)findViewById(R.id.addoption3);
        option4= (EditText)findViewById(R.id.addoption4);

        Realm.init(this);    //initialize to access database for this activity
        realm = Realm.getDefaultInstance();   //create a object for read and write database

        findViewById(R.id.addbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();  //open the database
                //database operation
                Questions obj = realm.createObject(Questions.class);

                obj.setQuestion(question.getText().toString());
                obj.setAnswer(Integer.parseInt(ans.getText().toString()));
                obj.setOption1(option1.getText().toString());
                obj.setOption2(option2.getText().toString());
                obj.setOption3(option3.getText().toString());
                obj.setOption4(option4.getText().toString());
                   //inserted all data to database
                realm.commitTransaction(); //close the database
                finish();
            }
        });
    }
}
