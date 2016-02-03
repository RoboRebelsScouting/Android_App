package com.example.saelmhurst.myapplication;
import java.util.ArrayList;

/**
 * Created by saelmhurst on 1/29/16.
 */
    enum enumBotAction {
        NoEvent,
        PickUpBall,
        DropBall,
        MissShot,
        Drive,
        CrossDef,
        FailDef,
        Climb,
        ScoreLow,
        ScoreHigh,
        Penalty,
        TechFoul,
        SpyBot,

        Portcullis,
        ChevalDeFrise,
        Moat,
        Drawbridge,
        Sallyport,
        RockWall,
        Terrain,
        LowBar,

        AutoPortcullis,
        AutoChevalDeFrise,
        AutoMoat,
        AutoDrawbridge,
        AutoSallyPort,
        AutoRockWall,
        AutoTerrain,
        AutoLowBar,

        AutoReachDef,
        AutoScoreHigh,
        AutoScoreLow,
        Challenge
    }



class InfoHeader {
    String eventNameInfo;
    String matchNumberInfo;
    String botNumberInfo;
    String allianceColorInfo;
    String scoutNameInfo;
    InfoHeader() {
        eventNameInfo = "";
        matchNumberInfo = "";
        botNumberInfo = "";
        allianceColorInfo = "";
        scoutNameInfo = "";
    }

    public void setInfoHeader(String strEvent, String strMatch, String strBot, String strAlliance, String strScout ) {
        eventNameInfo = strEvent;
        matchNumberInfo = strMatch;
        botNumberInfo = strBot;
        allianceColorInfo = strAlliance;
        scoutNameInfo = strScout;
    }
}



class RobotEvent {
    enumBotAction eBotAction;
    Integer robotActionTime;
    RobotEvent() {
        eBotAction = enumBotAction.NoEvent;
        robotActionTime = 0;
    }

    public void setBotAction (enumBotAction eAction, Integer iTime) {
        eBotAction = eAction;
        robotActionTime = iTime;
    }
}



public class InfoStorage {
    public InfoHeader infoHeader;
    public ArrayList <RobotEvent> robotEventArray;
    Integer index;

    InfoStorage() {
        infoHeader = new InfoHeader();
        robotEventArray = new ArrayList<RobotEvent>();
        index = 0;
    }
    void setHeader (String strEvent, String strMatch, String strBot, String strAlliance, String strScout) {
        infoHeader.setInfoHeader(strEvent, strMatch, strBot, strAlliance, strScout);
    }
    void AddAction(enumBotAction eAction, Integer iTime)
    {
        RobotEvent oAction = new RobotEvent();
        oAction.setBotAction(eAction, iTime);

        robotEventArray.add(index, oAction);
        ++index;
    }
}