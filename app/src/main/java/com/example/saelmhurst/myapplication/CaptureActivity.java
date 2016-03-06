package com.example.saelmhurst.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }

    public void onSubmit(View view){
        ToggleButton challenge = (ToggleButton) findViewById(R.id.Challenge);
        ToggleButton climb = (ToggleButton) findViewById(R.id.Climb);

        EditText penaltyPoints = (EditText) findViewById(R.id.PenaltyPoints);

        RobotEvent botEvent = new RobotEvent();
        if (challenge.isChecked()) {
            botEvent.eBotActionData = enumBotActionData.Succeed;
            botEvent.eBotAction = enumBotAction.Challenge;
            botEvent.robotActionTime = System.currentTimeMillis();
            FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);
        } else {
            botEvent.eBotActionData = enumBotActionData.Fail;
            botEvent.eBotAction = enumBotAction.Challenge;
            botEvent.robotActionTime = System.currentTimeMillis();
            FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);
        }

        botEvent = new RobotEvent();
        if (climb.isChecked()) {
            botEvent.eBotActionData = enumBotActionData.Succeed;
            botEvent.eBotAction = enumBotAction.Climb;
            botEvent.robotActionTime = System.currentTimeMillis();
            FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);
        } else {
            botEvent.eBotActionData = enumBotActionData.Fail;
            botEvent.eBotAction = enumBotAction.Climb;
            botEvent.robotActionTime = System.currentTimeMillis();
            FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);
        }


        if (!penaltyPoints.getText().toString().equals("")) {
            botEvent = new RobotEvent();
            botEvent.eBotAction = enumBotAction.Penalty;
            botEvent.eBotActionData = enumBotActionData.None;
            botEvent.robotActionTime = Integer.parseInt(penaltyPoints.getText().toString());
            FirstScouting.gameInfoStorage.robotEventArray.add(botEvent);
        }

        EditText totalScoreEdit = (EditText) findViewById(R.id.TotalScoreEdit);
        FirstScouting.gameInfoStorage.infoHeader.allianceTotalScore = totalScoreEdit.getText().toString();

        if (FirstScouting.gameInfoStorage.isExternalStorageWritable()) {
            FirstScouting.gameInfoStorage.correctCurrentEvent();
            if (!FirstScouting.gameInfoStorage.infoHeader.allianceTotalScore.equals("")) {
                FirstScouting.gameInfoStorage.csvCreate(this);
            } else {
                Toast.makeText(getApplicationContext(), "Put in Allied Total Score", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this, MainActivity.class);
        String eventNameInfo = FirstScouting.gameInfoStorage.infoHeader.eventNameInfo;
        String scoutNameInfo = FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo;
        FirstScouting.gameInfoStorage.Reset();
        FirstScouting.gameInfoStorage.infoHeader.eventNameInfo = eventNameInfo;
        FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo = scoutNameInfo;
        if (!FirstScouting.gameInfoStorage.infoHeader.allianceTotalScore.equals("")) {
            startActivity(intent);
        }
    }
}
