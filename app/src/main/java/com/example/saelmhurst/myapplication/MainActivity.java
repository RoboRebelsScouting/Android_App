package com.example.saelmhurst.myapplication;

import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ToggleButton;
import com.example.saelmhurst.myapplication.FirstScouting;
import java.io.Serializable;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.saelmhurst.myapplication.MESSAGE";
    private View viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.allianceToggle);
        toggleButton.getBackground().setColorFilter(new LightingColorFilter(0xFF0000FF, 0xFF0000FF));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Home", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });
        EditText eventTextEdit = (EditText) findViewById(R.id.eventText);
        EditText scoutTextEdit = (EditText) findViewById(R.id.scoutText);
        if (!FirstScouting.gameInfoStorage.infoHeader.eventNameInfo.equals("")) {
            eventTextEdit.setHint(FirstScouting.gameInfoStorage.infoHeader.eventNameInfo);
        }
        if (!FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo.equals("")) {
            scoutTextEdit.setHint(FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAllianceToggle(View view) {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.allianceToggle);
        if (toggleButton.isChecked()) {
            //RED
            toggleButton.getBackground().setColorFilter(new LightingColorFilter(0xFFFF0000,0xFFFF0000));
        } else {
            //BLUE
            toggleButton.getBackground().setColorFilter(new LightingColorFilter(0xFF0000FF, 0xFF0000FF));
        }
    }

    public void saveInfoGoNext(View view) {
        //FirstScouting.gameInfoStorage.Reset();

        long startTime = Calendar.getInstance().getTimeInMillis();
        EditText eventTextEdit = (EditText) findViewById(R.id.eventText);
        String eventTextString = eventTextEdit.getText().toString().trim();
        if (eventTextEdit.getText().toString().equals(null)) {
            eventTextString = FirstScouting.gameInfoStorage.infoHeader.eventNameInfo;
        }
        EditText matchTextEdit = (EditText) findViewById(R.id.matchText);
        String matchTextString = matchTextEdit.getText().toString().trim();
        EditText botTextEdit = (EditText) findViewById(R.id.botText);
        String botTextString = botTextEdit.getText().toString().trim();
        EditText scoutTextEdit = (EditText) findViewById(R.id.scoutText);
        String scoutTextString = scoutTextEdit.getText().toString().trim();
        if (scoutTextEdit.getText().toString().equals(null)) {
            scoutTextString = FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo;
        }
        ToggleButton allianceToggle = (ToggleButton) findViewById(R.id.allianceToggle);
        String allianceTextString;
        if (allianceToggle.isChecked()) {
            allianceTextString = "red";
        } else {
            allianceTextString = "blue";
        }
        FirstScouting.gameInfoStorage.setHeader(eventTextString, matchTextString, botTextString, allianceTextString, scoutTextString, startTime);
        Intent intent = new Intent(this, DefenseSelection.class);
        startActivity(intent);
    }
}
