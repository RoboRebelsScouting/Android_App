package com.example.saelmhurst.myapplication;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.util.Hashtable;

/*
 * Created by saelmhurst on 1/29/16.
 */
    enum enumBotActionData {
        None,
        Cross,
        Reach,
        Attempt,
        Fail,
        Succeed,
        PickUp,
        Drop,
        Miss,
        Score,
        Passage,
        Pinning,
        Defensive,
        Other
    }
    enum enumBotAction {
        Challenge,
        NoEvent,
        Ball,
        Drive,
        Climb,
        Penalty,
        TechFoul,
        EnterTeleop,
        ShootLow,
        ShootHigh,
        EnterAuto,
        Portcullis,
        Cheval,
        Moat,
        Drawbridge,
        SallyPort,
        RockWall,
        RoughTerrain,
        LowBar,
        Ramparts
    }



class InfoHeader {
    String eventNameInfo;
    String matchNumberInfo;
    String botNumberInfo;
    String allianceColorInfo;
    String scoutNameInfo;
    long startTime;
    InfoHeader() {
        Reset();
    }

    public void Reset() {
        eventNameInfo = "";
        matchNumberInfo = "";
        botNumberInfo = "";
        allianceColorInfo = "";
        scoutNameInfo = "";
        startTime = 0;
    }

    public void setInfoHeader(String strEvent, String strMatch, String strBot, String strAlliance, String strScout, long longStartTime ) {
        eventNameInfo = strEvent;
        matchNumberInfo = strMatch;
        botNumberInfo = strBot;
        allianceColorInfo = strAlliance;
        scoutNameInfo = strScout;
        startTime = longStartTime;
    }
}



class RobotEvent {
    enumBotAction eBotAction;
    enumBotActionData eBotActionData;
    long robotActionTime;
    RobotEvent() {
        eBotActionData = enumBotActionData.None;
        eBotAction = enumBotAction.NoEvent;
        robotActionTime = 0;
    }

    public void setBotAction (enumBotAction eAction) {
        eBotAction = eAction;
        robotActionTime = Calendar.getInstance().getTimeInMillis();
    }
}



public class InfoStorage implements Serializable
{
    public InfoHeader infoHeader = new InfoHeader();
    public ArrayList <RobotEvent> robotEventArray = new ArrayList<RobotEvent>();
    public enumBotAction defenseA = null;
    public enumBotAction defenseB = null;
    public enumBotAction defenseC = null;
    public enumBotAction defenseD = null;

    public enumBotAction[] defenses = new enumBotAction[4];

    Hashtable<enumBotAction, String> ht_BotActions = new Hashtable<>();


    Integer index;

    InfoStorage() {

        Reset();
    /*
        ht_BotActions.put(enumBotAction.NoEvent, "NOEVENT");
        ht_BotActions.put(enumBotAction.Drive, "DRIVE");
        ht_BotActions.put(enumBotAction.Climb, "CLIMB");
        ht_BotActions.put(enumBotAction.ScoreLow, "SCRLOW");
        ht_BotActions.put(enumBotAction.ScoreHigh, "SCRHIGH");
        ht_BotActions.put(enumBotAction.Penalty, "PENALTY");
        ht_BotActions.put(enumBotAction.TechFoul, "TECHFOUL");

        ht_BotActions.put(enumBotAction.Portcullis, "PRTCULS");
        ht_BotActions.put(enumBotAction.Cheval, "CHDEFRS");
        ht_BotActions.put(enumBotAction.Moat, "MOAT");
        ht_BotActions.put(enumBotAction.Drawbridge, "DRWBRG");
        ht_BotActions.put(enumBotAction.SallyPort, "SALPRT");
        ht_BotActions.put(enumBotAction.RockWall, "RCKWAL");
        ht_BotActions.put(enumBotAction.Terrain, "TERRN");
        ht_BotActions.put(enumBotAction.LowBar, "LOWBAR");
        ht_BotActions.put(enumBotAction.Ramparts, "RAMPAR");

        ht_BotActions.put(enumBotAction.AutoPortcullis, "APRTCUL");
        ht_BotActions.put(enumBotAction.AutoCheval, "ACHVDFR");
        ht_BotActions.put(enumBotAction.AutoMoat, "AUTMOT");
        ht_BotActions.put(enumBotAction.AutoDrawbridge, "AUTDRB");
        ht_BotActions.put(enumBotAction.AutoSallyPort, "AUTSPRT");
        ht_BotActions.put(enumBotAction.AutoRockWall, "AUTRWAL");
        ht_BotActions.put(enumBotAction.AutoTerrain, "AUTTERN");
        ht_BotActions.put(enumBotAction.AutoLowBar, "AUTLWBR");
        ht_BotActions.put(enumBotAction.AutoRamparts, "AUTRAM");

        ht_BotActions.put(enumBotAction.AutoReachDef, "AUTRDEF");
        ht_BotActions.put(enumBotAction.AutoScoreHigh, "AUTSCRH");
        ht_BotActions.put(enumBotAction.AutoScoreLow, "AUTSCRL");

        ht_BotActions.put(enumBotAction.Challenge, "CHALNGE");
    */

    }

    void Reset()
    {
        infoHeader.Reset();
        robotEventArray.clear();
        //index = 0;

    }
    void setHeader (String strEvent, String strMatch, String strBot, String strAlliance, String strScout, long longStartTime) {
        infoHeader.setInfoHeader(strEvent, strMatch, strBot, strAlliance, strScout, longStartTime);
    }
    void AddAction(enumBotAction eAction)
    {
        RobotEvent oAction = new RobotEvent();
        oAction.setBotAction(eAction);

        //robotEventArray.add(index, oAction);
        robotEventArray.add(oAction);
        //++index;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), albumName);
        if (!file.mkdirs()) {
            Log.e("ERROR", "Directory NOT Created");
        }
        return file;
    }
    void csvCreate() {
        String fileName= infoHeader.eventNameInfo + "-" + infoHeader.matchNumberInfo + "-" + infoHeader.allianceColorInfo + "-" + infoHeader.botNumberInfo + ".csv";
        File directory = getAlbumStorageDir("/FRC2016");
        File file = new File(directory,fileName);
        try {
            FileWriter writer = new FileWriter(file);
            String lineOne = infoHeader.eventNameInfo + "," +
                    infoHeader.matchNumberInfo + "," +
                    infoHeader.allianceColorInfo + "," +
                    infoHeader.botNumberInfo + "," +
                    infoHeader.scoutNameInfo;
            writer.write(lineOne + "\n");
            for (int c = 0; c < robotEventArray.size(); c++) {
                String output;
                if ((robotEventArray.get(c).eBotAction.equals(enumBotAction.Drawbridge))
                        || (robotEventArray.get(c).equals(enumBotAction.Cheval))
                        || (robotEventArray.get(c).equals(enumBotAction.Ramparts))
                        || (robotEventArray.get(c).equals(enumBotAction.LowBar))
                        || (robotEventArray.get(c).equals(enumBotAction.RoughTerrain))
                        || (robotEventArray.get(c).equals(enumBotAction.RockWall))
                        || (robotEventArray.get(c).equals(enumBotAction.Moat))
                        || (robotEventArray.get(c).equals(enumBotAction.SallyPort))
                        || (robotEventArray.get(c).equals(equals(enumBotAction.Portcullis)))){
                    output = robotEventArray.get(c).robotActionTime + "," +
                            "Defense," +
                            robotEventArray.get(c).eBotAction.toString() + "," +
                            robotEventArray.get(c).eBotActionData;
                } else {
                    output =  robotEventArray.get(c).robotActionTime + "," +
                            robotEventArray.get(c).eBotAction.toString() + "," +
                            robotEventArray.get(c).eBotActionData;
                }
                writer.write(output + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Log.e("ERROR","File NOT Created",e);
        }
    }
}