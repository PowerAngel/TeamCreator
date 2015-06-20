package com.example.golvmopp.teamcreator;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
/**
 * Created by jonatan on 2015-06-16.
 */
public class Team implements Parcelable{
    private ArrayList<String> TeamList = new ArrayList<String>();
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
    public ArrayList<String> getTeam(){return TeamList;}
    public void addMember(String name){TeamList.add(name);}

    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    private Team(Parcel in)
    {
        TeamList = in.createStringArrayList();
        teamNumber = in.readInt();
    }

    //@Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeStringList(TeamList);
        out.writeInt(teamNumber);
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
