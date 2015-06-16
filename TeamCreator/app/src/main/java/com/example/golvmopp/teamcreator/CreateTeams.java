package com.example.golvmopp.teamcreator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class CreateTeams extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teams);
        Player player1 = new Player("Jonte",1);
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
        PlayersArray.add(player6);

        final ListView liteView_Players = (ListView) this.findViewById(R.id.listView_Players);


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
}
