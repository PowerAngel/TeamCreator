package com.example.golvmopp.teamcreator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FiveTeams extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<ArrayList> AllNamesArray = new ArrayList<ArrayList>();
    ArrayList<Team> TeamsArray = new ArrayList<Team>();
    String myLogTag = "myLogTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_teams);
        TeamsArray = getIntent().getParcelableArrayListExtra("TeamsArray");
        final ListView listView_Team1 = (ListView) this.findViewById(R.id.listView_Team1);
        final ListView listView_Team2 = (ListView) this.findViewById(R.id.listView_Team2);
        final ListView listView_Team3 = (ListView) this.findViewById(R.id.listView_Team3);
        final ListView listView_Team4 = (ListView) this.findViewById(R.id.listView_Team4);
        final ListView listView_Team5 = (ListView) this.findViewById(R.id.listView_Team5);
        for(int i = 0; i < TeamsArray.size(); i++)
        {
            String names;
            ArrayList<String> PresentedArray = new ArrayList<String>();
            for(int j = 0; j < TeamsArray.get(i).getTeam().size(); j+=2)
            {
                names = TeamsArray.get(i).getTeam().get(j);
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
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNamesArray.get(3));
        listView_Team4.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AllNamesArray.get(4));
        listView_Team5.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_five_teams, menu);
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
