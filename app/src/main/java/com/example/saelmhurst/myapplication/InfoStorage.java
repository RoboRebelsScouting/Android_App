package com.example.saelmhurst.myapplication;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

/*
 * Created by saelmhurst on 1/29/16.
 */
    enum enumBotActionData {
        None,
        Cross,
        Reach,
        Approach,
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
        DefensiveMove,
        Climb,
        Penalty,
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
    String allianceTotalScore;
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
        String fileName= infoHeader.eventNameInfo.trim() + "-" +
                infoHeader.matchNumberInfo.trim() + "-" +
                infoHeader.allianceColorInfo.trim() + "-" +
                infoHeader.botNumberInfo.trim() + "-" +
                ".csv";

        File directory = getAlbumStorageDir("/FRC2016");
        File file = new File(directory,fileName);
        try {
            FileWriter writer = new FileWriter(file);
            String lineOne = infoHeader.eventNameInfo.trim() + "," +
                    infoHeader.matchNumberInfo.trim() + "," +
                    infoHeader.allianceColorInfo.trim() + "," +
                    infoHeader.botNumberInfo.trim() + "," +
                    infoHeader.scoutNameInfo.trim() + "," +
                    infoHeader.allianceTotalScore.trim();


            writer.write(lineOne + "\n");


            for (int c = 0; c < robotEventArray.size(); c++) {
                String output = "";
                Format format = new SimpleDateFormat("yyyy-MM-dd-HH:mm;ss");

                if ((robotEventArray.get(c).eBotAction.equals(enumBotAction.Drawbridge))
                        || (robotEventArray.get(c).equals(enumBotAction.Cheval))
                        || (robotEventArray.get(c).equals(enumBotAction.Ramparts))
                        || (robotEventArray.get(c).equals(enumBotAction.LowBar))
                        || (robotEventArray.get(c).equals(enumBotAction.RoughTerrain))
                        || (robotEventArray.get(c).equals(enumBotAction.RockWall))
                        || (robotEventArray.get(c).equals(enumBotAction.Moat))
                        || (robotEventArray.get(c).equals(enumBotAction.SallyPort))
                        || (robotEventArray.get(c).equals(enumBotAction.Portcullis))) {

                    Date date = new Date(robotEventArray.get(c).robotActionTime);

                    output = format.format(date) + "," +
                            "Defense," +
                            robotEventArray.get(c).eBotAction.toString() + "," +
                            robotEventArray.get(c).eBotActionData;

                } else if (robotEventArray.get(c).equals(enumBotAction.Penalty)) {

                    Date date = new Date(Calendar.getInstance().getTimeInMillis());

                    output =  format.format(date) + "," +
                            robotEventArray.get(c).robotActionTime + "," +
                            robotEventArray.get(c).eBotAction.toString() + "," +
                            robotEventArray.get(c).eBotActionData;

                } else {
                    Date date = new Date(robotEventArray.get(c).robotActionTime);

                    output = format.format(date) + "," +
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

    public void correctCurrentEvent() {
        long time = System.currentTimeMillis();
        Format format = new SimpleDateFormat("yyyy-MM-dd-HH:mm;ss");
        String dateString = format.format(time);
        String[] yearMonthDayHour = dateString.split("-");
        if (yearMonthDayHour[1].equals("03")) {
            if ((Integer.parseInt(yearMonthDayHour[2]) > 14) && (Integer.parseInt(yearMonthDayHour[2]) < 10)) {
                infoHeader.eventNameInfo = "2016mawor";
            } else if ((Integer.parseInt(yearMonthDayHour[2]) > 27) && (Integer.parseInt(yearMonthDayHour[2]) < 23)) {
                infoHeader.eventNameInfo = "2016nhdur";
            }
        } else if (yearMonthDayHour[1].equals("04")) {
            if ((Integer.parseInt(yearMonthDayHour[2]) > 17) && (Integer.parseInt(yearMonthDayHour[2]) < 12)) {
                infoHeader.eventNameInfo = "2016necmp";
            }
        }
    }
}