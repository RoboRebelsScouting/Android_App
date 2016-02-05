package com.example.saelmhurst.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Calendar;
import java.util.List;

import com.example.saelmhurst.myapplication.FirstScouting;

/**
 * Created by saelmhurst on 1/30/16.
 */
public class AutoPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auto_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onButton(View view) {

    }

    public void onHit(View view) {
        ImageButton btn = (ImageButton) view;
        String strTag = btn.getTag().toString();
        enumBotAction eAction = enumBotAction.valueOf(strTag);
        FirstScouting.gameInfoStorage.AddAction(eAction);

        Resources resources = getResources();
        int id = resources.getIdentifier(strTag + "0", "id", getApplicationContext().getPackageName());
        Button button = (Button) findViewById(id);

        String buttonText = button.getText().toString();
        String[] array = buttonText.split(":");
        int crossed = Integer.parseInt(array[1].trim());
        crossed++;
        button.setText(array[0] + ": " + crossed);

    }
}
