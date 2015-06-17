package com.example.golvmopp.teamcreator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ThreeTeams extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<ArrayList> AllNamesArray = new ArrayList<ArrayList>();
    ArrayList<Team> TeamsArray = new ArrayList<Team>();
    String myLogTag = "myLogTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_teams);
        TeamsArray = getIntent().getParcelableArrayListExtra("TeamsArray");
        final ListView listView_Team1 = (ListView) this.findViewById(R.id.listView_Team1);
        final ListView listView_Team2 = (ListView) this.findViewById(R.id.listView_Team2);
        final ListView listView_Team3 = (ListView) this.findViewById(R.id.listView_Team3);
        for(int i = 0; i < TeamsArray.size(); i++)
        {
            String names;
            ArrayList<String> PresentedArray = new ArrayList<String>();
            for(int j = 0; j < TeamsArray.get(i).getTeam().size(); j+=2)
            {
                if(TeamsArray.get(i).getTeam().size() - j > 1)
                    names = TeamsArray.get(i).getTeam().get(j) + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + TeamsArray.get(i).getTeam().get(j+1);
                else if(TeamsArray.get(i).getTeam().size() - j > 0)
                    names = TeamsArray.get(i).getTeam().get(j);
                else
                    names = "";
                PresentedArray.add(names);
            }
            AllNamesArray.add(PresentedArray);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNamesArray.get(0));
        listView_Team1.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNamesArray.get(1));
        listView_Team2.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNamesArray.get(2));
        listView_Team3.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_teams, menu);
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
}
