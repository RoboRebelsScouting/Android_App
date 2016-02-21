package com.example.saelmhurst.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

        EditText penaltyPinning = (EditText) findViewById(R.id.PinningPenalty);
        EditText penaltyPassage = (EditText) findViewById(R.id.PassagePenalty);
        EditText penaltyDefensive = (EditText) findViewById(R.id.DefensivePenalty);
        EditText penaltyOther = (EditText) findViewById(R.id.OtherPenalty);

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

        botEvent.eBotAction = enumBotAction.Penalty;

        if (!penaltyDefensive.getText().toString().equals("")) {
            botEvent.eBotActionData = enumBotActionData.Defensive;
            botEvent.robotActionTime = Integer.parseInt(penaltyDefensive.getText().toString());
        }

        if (!penaltyPassage.getText().toString().equals("")) {
            botEvent.eBotActionData = enumBotActionData.Passage;
            botEvent.robotActionTime = Integer.parseInt(penaltyPassage.getText().toString());
        }

        if (!penaltyPinning.getText().toString().equals("")) {
            botEvent.eBotActionData = enumBotActionData.Pinning;
            botEvent.robotActionTime = Integer.parseInt(penaltyPinning.getText().toString());
        }

        if (!penaltyOther.getText().toString().equals("")) {
            botEvent.eBotActionData = enumBotActionData.Other;
            botEvent.robotActionTime = Integer.parseInt(penaltyOther.getText().toString());
        }

        EditText totalScoreEdit = (EditText) findViewById(R.id.TotalScoreEdit);
        FirstScouting.gameInfoStorage.infoHeader.allianceTotalScore = totalScoreEdit.getText().toString();

        if (FirstScouting.gameInfoStorage.isExternalStorageWritable()) {
            FirstScouting.gameInfoStorage.correctCurrentEvent();
            FirstScouting.gameInfoStorage.csvCreate();
        }

        Intent intent = new Intent(this, MainActivity.class);
        String eventNameInfo = FirstScouting.gameInfoStorage.infoHeader.eventNameInfo;
        String scoutNameInfo = FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo;
        FirstScouting.gameInfoStorage.Reset();
        FirstScouting.gameInfoStorage.infoHeader.eventNameInfo = eventNameInfo;
        FirstScouting.gameInfoStorage.infoHeader.scoutNameInfo = scoutNameInfo;
        startActivity(intent);
    }
}
