package com.example.saelmhurst.myapplication;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void saveInfoGoNext(View view) {
        FirstScouting.gameInfoStorage.Reset();

        long startTime = Calendar.getInstance().getTimeInMillis();
        EditText eventTextEdit = (EditText) findViewById(R.id.eventText);
        String eventTextString = eventTextEdit.getText().toString();
        EditText matchTextEdit = (EditText) findViewById(R.id.matchText);
        String matchTextString = matchTextEdit.getText().toString();
        EditText botTextEdit = (EditText) findViewById(R.id.botText);
        String botTextString = botTextEdit.getText().toString();
        EditText scoutTextEdit = (EditText) findViewById(R.id.scoutText);
        String scoutTextString = scoutTextEdit.getText().toString();
        ToggleButton allianceToggle = (ToggleButton) findViewById(R.id.allianceToggle);
        String allianceTextString;
        if (allianceToggle.isChecked() == true) {
            allianceTextString = "red";
        } else {
            allianceTextString = "blue";
        }
        FirstScouting.gameInfoStorage.setHeader(eventTextString, matchTextString, botTextString, allianceTextString, scoutTextString, startTime);

        Intent intent = new Intent(this, AutoPageActivity.class);
        startActivity(intent);
    }
}
