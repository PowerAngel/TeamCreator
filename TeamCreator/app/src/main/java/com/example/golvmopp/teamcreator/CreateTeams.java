package com.example.golvmopp.teamcreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.System;
import java.util.ArrayList;
import java.util.Random;


public class CreateTeams extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    ArrayList<String> NameArray = new ArrayList<String>();
    ArrayList<Team> TeamsArray = new ArrayList<Team>();
    String myLogTag = "myLogTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teams);
        PlayersArray = getIntent().getParcelableArrayListExtra("ChosenArray");

        /*Player player1 = new Player("Jonte",1);
        Player player2 = new Player("David",1);
        Player player3 = new Player("Bjorn",1337);
        Player player4 = new Player("John",1);
        Player player5 = new Player("Josef",1);
        Player player6 = new Player("Plinge",1);
        PlayersArray.add(player1);
        PlayersArray.add(player2);
        PlayersArray.add(player3);
        PlayersArray.add(player4);
        PlayersArray.add(player5);
        PlayersArray.add(player6);*/
        for(int i = 0; i < PlayersArray.size(); i++){
            String name = PlayersArray.get(i).getName();
            NameArray.add(i,name);
        }
        final ListView listView_Players = (ListView) this.findViewById(R.id.listView_Players);
        final TextView textView_NumberOfTeams = (TextView) this.findViewById(R.id.txtView_NumberOfTeams);
        final Spinner spn_NumberOfTeams = (Spinner) this.findViewById(R.id.spn_NumberOfTeams);
        ImageButton btn_MakeTeams = (ImageButton) this.findViewById(R.id.btn_MakeTeams);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NameArray);
        listView_Players.setAdapter(adapter);

        String[] items = new String[] {"2", "3", "4", "5", "6", "7", "8"};
        Spinner spinner = (Spinner) this.findViewById(R.id.spn_NumberOfTeams);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_MakeTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfTeams = Integer.parseInt(spn_NumberOfTeams.getSelectedItem().toString());
                double teamCapacity = (double) NameArray.size() / (double) numberOfTeams;
                Random rand = new Random();
                int n = rand.nextInt(NameArray.size());
                ArrayList<String> NameList = new ArrayList<String>();
                NameList = NameArray;
                for(int i = 1; i <= numberOfTeams; i++)
                {
                    Team team = new Team(i);
                    TeamsArray.add(team);
                }
                int ti = 0;
                int nameList = NameList.size();
                for(int i = 0; i < nameList; i++)
                {
                    TeamsArray.get(ti).addMember(NameList.get(n));
                    NameList.remove(n);
                    if(NameList.size() > 0)
                        n = rand.nextInt(NameList.size());
                    ti++;
                    if(ti == numberOfTeams)
                        ti = 0;

                }



                try {
                    Intent intent;
                    if(numberOfTeams == 2)
                        intent = new Intent(getApplicationContext(), TwoTeams.class);
                    else if(numberOfTeams == 3)
                        intent = new Intent(getApplicationContext(), ThreeTeams.class);
                    else if(numberOfTeams == 4)
                        intent = new Intent(getApplicationContext(), FourTeams.class);
                    else if(numberOfTeams == 5)
                        intent = new Intent(getApplicationContext(), FiveTeams.class);
                    else if(numberOfTeams == 6)
                        intent = new Intent(getApplicationContext(), ThreeTeams.class);
                    else
                        intent = null; //ain't gonna happen

                    intent.putParcelableArrayListExtra("TeamsArray", TeamsArray);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.v("myLogTag", "Nu gick n�got fel");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /*public void TeamsScreen(View view)
    {

        Intent intent = new Intent(getApplicationContext(), TwoTeams.class);
        intent.putParcelableArrayListExtra("TeamsArray", TeamsArray);
        startActivity(intent);
    }*/
}
