package com.example.saelmhurst.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.media.Image;
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
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.saelmhurst.myapplication.FirstScouting;

/**
 * Created by saelmhurst on 1/30/16.
 */
public class AutoPageActivity extends AppCompatActivity {

    void setImageAndRow(int defenseNum ) {
        String tagString = FirstScouting.gameInfoStorage.defenses[defenseNum].toString();
        String pngString = FirstScouting.gameInfoStorage.defenses[defenseNum].toString().toLowerCase();
        int defenseResID = getResources().getIdentifier(pngString, "drawable", getApplicationContext().getPackageName());

        if (defenseNum == 0) {
            ImageButton dFence = (ImageButton) findViewById(R.id.dFenceA);
            Button crossNumber = (Button) findViewById(R.id.crossANumber);
            Button failNumber = (Button) findViewById(R.id.failANumber);
            ToggleButton attempt = (ToggleButton) findViewById(R.id.AttemptedA);
            dFence.setImageResource(defenseResID);
            crossNumber.setTag(tagString + ":Cross");
            failNumber.setTag(tagString + ":Fail");
            attempt.setTag(tagString+":Approach");

        } else if (defenseNum == 1) {
            ImageButton dFence = (ImageButton) findViewById(R.id.dFenceB);
            Button crossNumber = (Button) findViewById(R.id.crossBNumber);
            Button failNumber = (Button) findViewById(R.id.failBNumber);
            ToggleButton attempt = (ToggleButton) findViewById(R.id.AttemptedB);
            dFence.setImageResource(defenseResID);
            crossNumber.setTag(tagString + ":Cross");
            failNumber.setTag(tagString + ":Fail");
            attempt.setTag(tagString+":Approach");

        } else if (defenseNum == 2) {
            ImageButton dFence = (ImageButton) findViewById(R.id.dFenceC);
            Button crossNumber = (Button) findViewById(R.id.crossCNumber);
            Button failNumber = (Button) findViewById(R.id.failCNumber);
            ToggleButton attempt = (ToggleButton) findViewById(R.id.AttemptedC);
            dFence.setImageResource(defenseResID);
            crossNumber.setTag(tagString + ":Cross");
            failNumber.setTag(tagString + ":Fail");
            attempt.setTag(tagString+":Approach");

        } else {
            ImageButton dFence = (ImageButton) findViewById(R.id.dFenceD);
            Button crossNumber = (Button) findViewById(R.id.crossDNumber);
            Button failNumber = (Button) findViewById(R.id.failDNumber);
            ToggleButton attempt = (ToggleButton) findViewById(R.id.AttemptedD);
            dFence.setImageResource(defenseResID);
            crossNumber.setTag(tagString + ":Cross");
            failNumber.setTag(tagString + ":Fail");
            attempt.setTag(tagString+":Approach");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Automatic", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });

        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        for (int c = 0; c < FirstScouting.gameInfoStorage.defenses.length; c++) {
            setImageAndRow(c);
        }

    }

    public void onButton(View view) {
        View btn = view;
        String strTag = view.getTag().toString();
        String[] arrayTags = strTag.split(":");

        if (arrayTags[1].equalsIgnoreCase("Approach")) {
            btn = (ToggleButton) view;
        } else {
            btn = (Button) view;
        }

        enumBotAction actionToBeRemoved = enumBotAction.valueOf(arrayTags[0]);
        enumBotActionData actionToBeRemovedData = enumBotActionData.valueOf(arrayTags[1]);
        Boolean removed = false;
        if (FirstScouting.gameInfoStorage.robotEventArray.size() > 0) {
            for (int a = FirstScouting.gameInfoStorage.robotEventArray.size() - 1; a >= 0; a = a - 1) {
                if (FirstScouting.gameInfoStorage.robotEventArray.get(a).eBotAction.equals(actionToBeRemoved)) {
                    if (FirstScouting.gameInfoStorage.robotEventArray.get(a).eBotActionData == actionToBeRemovedData) {
                        if (!removed) {
                            FirstScouting.gameInfoStorage.robotEventArray.remove(a);
                            removed = true;
                            if (arrayTags[1].equalsIgnoreCase("Approach") == false) {
                                Button button = (Button) btn;
                                String buttonText = button.getText().toString();
                                String[] array = buttonText.split(":");
                                int crossed = Integer.parseInt(array[1].trim());
                                crossed = crossed - 1;
                                button.setText(array[0] + ":" + crossed);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onToggleDef(View view) {
        ToggleButton tView = (ToggleButton)view;
        if (tView.isChecked()) {
            onDefenseHit(view);
            ColorStateList red = tView.getTextColors();
            tView.setTextColor(tView.getHintTextColors());
            tView.setHintTextColor(red);
        } else {
            onButton(view);
            ColorStateList green = tView.getTextColors();
            tView.setTextColor(tView.getHintTextColors());
            tView.setHintTextColor(green);
        }
    }

    public void  onNonDefense (View view) {
        String[] tagArray = view.getTag().toString().split(":");
        enumBotAction botAction = enumBotAction.valueOf(tagArray[0]);
        enumBotActionData botActionData = enumBotActionData.valueOf(tagArray[1]);
        RobotEvent botEvent = new RobotEvent();
        botEvent.eBotAction = botAction;
        botEvent.eBotActionData = botActionData;
        botEvent.robotActionTime = System.currentTimeMillis();
        FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);


        Resources resources = getResources();
        String resourceString = tagArray[0].toLowerCase() + tagArray[1] + "Number";
        int id = resources.getIdentifier(resourceString, "id", getApplicationContext().getPackageName());
        Button button = (Button) findViewById(id);
        String buttonText = button.getText().toString();
        String[] array = buttonText.split(":");
        int crossedOrFailed = Integer.parseInt(array[1].trim());
        crossedOrFailed++;
        button.setText(array[0] + ": " + crossedOrFailed);
    }


    // react to defense cross or fail button
    public void onDefenseHit(View view) {
        String strTag = view.getTag().toString();
        String[] strTagArray = strTag.split(":");

        if (strTagArray.length == 2) {
            enumBotAction eAction = enumBotAction.NoEvent;
            enumBotActionData eActionData = enumBotActionData.None;
            // for A defense - get defense in defenses[0]
            if (!strTagArray[1].equals("Approach")) {
                if (strTagArray[1].equals("A")) {
                    eAction = FirstScouting.gameInfoStorage.defenses[0];
                } else if (strTagArray[1].equals("B")) {
                    eAction = FirstScouting.gameInfoStorage.defenses[1];
                } else if (strTagArray[1].equals("C")) {
                    eAction = FirstScouting.gameInfoStorage.defenses[2];
                } else if (strTagArray[1].equals("D")) {
                    eAction = FirstScouting.gameInfoStorage.defenses[3];
                } else {
                    eAction = enumBotAction.valueOf(strTagArray[1]);
                }
                eActionData = enumBotActionData.valueOf(strTagArray[0]);
            } else {
                eActionData = enumBotActionData.Approach;
                eAction = enumBotAction.valueOf(strTagArray[0]);
            }


            RobotEvent rEvent = new RobotEvent();
            rEvent.eBotAction = eAction;
            rEvent.eBotActionData = eActionData;
            rEvent.robotActionTime = System.currentTimeMillis();

            FirstScouting.gameInfoStorage.robotEventArray.add(rEvent);

            // update text button
            if (eActionData != enumBotActionData.Approach) {
                Resources resources = getResources();
                String resourceString = strTagArray[0].toLowerCase() + strTagArray[1] + "Number";
                int id = resources.getIdentifier(resourceString, "id", getApplicationContext().getPackageName());
                Button button = (Button) findViewById(id);
                String buttonText = button.getText().toString();
                String[] array = buttonText.split(":");
                int crossedOrFailed = Integer.parseInt(array[1].trim());
                crossedOrFailed++;
                button.setText(array[0] + ": " + crossedOrFailed);
            }
        }
    }

    public void goTeleopOrCapture(View view) {
        ToggleButton toggle = (ToggleButton) view;
        TextView mode = (TextView) findViewById(R.id.mode);
        ToggleButton approachA = (ToggleButton) findViewById(R.id.AttemptedA);
        ToggleButton approachB = (ToggleButton) findViewById(R.id.AttemptedB);
        ToggleButton approachC = (ToggleButton) findViewById(R.id.AttemptedC);
        ToggleButton approachD = (ToggleButton) findViewById(R.id.AttemptedD);
        ToggleButton approachE = (ToggleButton) findViewById(R.id.AttemptedLowBar);

        if (toggle.isChecked()) {
            mode.setText("Teleop");
            RobotEvent rEvent = new RobotEvent();
            rEvent.robotActionTime = System.currentTimeMillis();
            rEvent.eBotAction = enumBotAction.EnterTeleop;
            rEvent.eBotActionData = enumBotActionData.None;
            FirstScouting.gameInfoStorage.robotEventArray.add(rEvent);


            approachA.setVisibility(View.INVISIBLE);
            approachB.setVisibility(View.INVISIBLE);
            approachC.setVisibility(View.INVISIBLE);
            approachD.setVisibility(View.INVISIBLE);
            approachE.setVisibility(View.INVISIBLE);


        } else {
            mode.setText("Autonomous");
            approachA.setVisibility(View.VISIBLE);
            approachB.setVisibility(View.VISIBLE);
            approachC.setVisibility(View.VISIBLE);
            approachD.setVisibility(View.VISIBLE);
            approachE.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivity(intent);
        }
    }
}
