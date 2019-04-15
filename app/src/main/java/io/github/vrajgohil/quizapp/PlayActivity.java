package io.github.vrajgohil.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class PlayActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        realm = Realm.getDefaultInstance();
        ArrayList<Questions> question = new ArrayList<Questions>();
        RealmResults<Questions> results = realm.where(Questions.class).findAllAsync();
        //fetching the data
        results.load();
        String output = "";
        for (Questions information : results) {
            output += information.toString();
        }


        TextView questionView = (TextView) findViewById(R.id.questionView);
        questionView.setText(output);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
