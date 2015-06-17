package com.example.golvmopp.teamcreator;
import java.util.ArrayList;
/**
 * Created by jonatan on 2015-06-16.
 */
public class Team {
    private ArrayList Team = new ArrayList();
    private int teamNumber;
    public Team()
    {

    }
    public Team(int team_Number)
    {
        teamNumber = team_Number;
    }
    public void setTeamNumber(int n){teamNumber = n;}
    public int getTeamNumber(){return teamNumber;}
    public ArrayList getTeam(){return Team;}
    public void addMember(String name){Team.add(name);}
}
