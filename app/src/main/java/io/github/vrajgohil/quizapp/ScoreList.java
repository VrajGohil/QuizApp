package io.github.vrajgohil.quizapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreList extends ArrayAdapter {
    private Activity context;
    private List<Score> scoreList;

    public ScoreList(Activity context, List<Score> scoreList){
        super(context,R.layout.activity_score);
        this.context=context;
        this.scoreList=scoreList;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.activity_score,null,true);
        TextView textViewName= listViewItem.findViewById(R.id.textViewName);
        TextView textViewScore= listViewItem.findViewById(R.id.textViewScore);

        Score score=scoreList.get(position);
        textViewName.setText(score.getScoreName());
        textViewScore.setText(score.getScoreValue());
        return listViewItem;
    }
}

