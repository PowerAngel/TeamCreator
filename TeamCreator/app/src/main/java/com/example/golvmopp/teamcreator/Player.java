package com.example.golvmopp.teamcreator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Golvmopp on 2015-06-16.
 */
public class Player implements Parcelable
{
    private String name;
    private double skill;

    public Player()
    {

    }

    public Player(String name, double skill)
    {
        super();
        this.name = name;
        this.skill = skill;
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public double getSkill(){return skill;}
    public void setSkill(){this.skill = skill;}

    @Override
    public int describeContents()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    private Player(Parcel in)
    {
        name = in.readString();
        skill = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeString(name);
        out.writeDouble(skill);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
