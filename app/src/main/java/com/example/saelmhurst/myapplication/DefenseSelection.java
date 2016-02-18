package com.example.saelmhurst.myapplication;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ToggleButton;

public class DefenseSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defense_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Select Defenses", Snackbar.LENGTH_LONG)
                        .setAction("" +
                                "", null).show();
            }
        });
    }

    public void goAuto(View view) {



        ToggleButton toggle = null;
        String[] toggleArray = null;
        for (int c = 0; c < FirstScouting.gameInfoStorage.defenses.length; c++) {
            if (c == 0) {
                toggle = (ToggleButton) findViewById(R.id.toggleA);
                toggleArray = toggle.getTag().toString().split(":");
            } else if (c == 1) {
                toggle = (ToggleButton) findViewById(R.id.toggleB);
                toggleArray = toggle.getTag().toString().split(":");
            }  else if (c == 2) {
                 toggle = (ToggleButton) findViewById(R.id.toggleC);
               toggleArray = toggle.getTag().toString().split(":");
            }  else if (c == 3) {
                toggle = (ToggleButton) findViewById(R.id.toggleD);
                toggleArray = toggle.getTag().toString().split(":");
            }
            if (toggle.isChecked()) {
                FirstScouting.gameInfoStorage.defenses[c] = enumBotAction.valueOf(toggleArray[1]);
            } else {
                FirstScouting.gameInfoStorage.defenses[c] = enumBotAction.valueOf(toggleArray[0]);
            }
        }

        FirstScouting.gameInfoStorage.AddAction(enumBotAction.EnterAuto);
        Intent intent = new Intent(this, AutoPageActivity.class);
        startActivity(intent);
    }

}
