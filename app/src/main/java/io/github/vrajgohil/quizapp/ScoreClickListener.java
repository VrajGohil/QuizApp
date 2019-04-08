package io.github.vrajgohil.quizapp;

import android.view.View;
import android.widget.Toast;

public class ScoreClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(),"Open Scores",Toast.LENGTH_SHORT).show();
    }
}
