package com.example.golvmopp.teamcreator;

/**
 * Created by Golvmopp on 2015-06-16.
 */
public class Player
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
}
