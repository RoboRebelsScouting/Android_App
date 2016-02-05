package com.example.saelmhurst.myapplication;
import java.util.ArrayList;
import java.util.Calendar;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by saelmhurst on 1/29/16.
 */
    enum enumBotAction {
        NoEvent,
        PickUpBall,
        DropBall,
        MissShot,
        Drive,
        Climb,
        Penalty,
        TechFoul,
        SpyBot,
        //Reach = 12
        ReachDef,
        FailDef,
        Portcullis,
        ScoreLow,
        ScoreHigh,
        Cheval,
        Moat,
        Drawbridge,
        SallyPort,
        RockWall,
        Terrain,
        LowBar,
        Ramparts,
        //AutoReachDef = 23
        AutoReachDef,
        AutoFailDef,
        AutoScoreHigh,
        AutoScoreLow,
        Challenge,
        AutoPortcullis,
        AutoCheval,
        AutoMoat,
        AutoDrawbridge,
        AutoSallyPort,
        AutoRockWall,
        AutoTerrain,
        AutoLowBar,
        AutoRamparts
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
    long robotActionTime;
    RobotEvent() {
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

    Hashtable<enumBotAction, String> ht_BotActions = new Hashtable<>();


    Integer index;

    InfoStorage() {

        Reset();

        ht_BotActions.put(enumBotAction.NoEvent, "NOEVENT");
        ht_BotActions.put(enumBotAction.PickUpBall, "PKUPBALL");
        ht_BotActions.put(enumBotAction.DropBall, "DROPBALL");
        ht_BotActions.put(enumBotAction.MissShot, "MISSSHOT");
        ht_BotActions.put(enumBotAction.Drive, "DRIVE");
        ht_BotActions.put(enumBotAction.ReachDef, "RECHDEF");
        ht_BotActions.put(enumBotAction.FailDef, "FAILDEF");
        ht_BotActions.put(enumBotAction.Climb, "CLIMB");
        ht_BotActions.put(enumBotAction.ScoreLow, "SCRLOW");
        ht_BotActions.put(enumBotAction.ScoreHigh, "SCRHIGH");
        ht_BotActions.put(enumBotAction.Penalty, "PENALTY");
        ht_BotActions.put(enumBotAction.TechFoul, "TECHFOUL");
        ht_BotActions.put(enumBotAction.SpyBot, "SPYBOT");

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

    }


    void Reset()
    {
        infoHeader.Reset();
        robotEventArray.clear();
        index = 0;

    }
    void setHeader (String strEvent, String strMatch, String strBot, String strAlliance, String strScout, long longStartTime) {
        infoHeader.setInfoHeader(strEvent, strMatch, strBot, strAlliance, strScout, longStartTime );
    }
    void AddAction(enumBotAction eAction)
    {
        RobotEvent oAction = new RobotEvent();
        oAction.setBotAction(eAction);

        robotEventArray.add(index, oAction);
        ++index;
    }
}