package com.example.golvmopp.teamcreator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class CreateTeams extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    ArrayList<String> NameArray = new ArrayList<String>();

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
        Button btn_MakeTeams = (Button) this.findViewById(R.id.btn_MakeTeams);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NameArray);
        listView_Players.setAdapter(adapter);

        String[] items = new String[] {"2", "3", "4", "5", "6"};
        Spinner spinner = (Spinner) this.findViewById(R.id.spn_NumberOfTeams);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_MakeTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NumberOfTeams = spn_NumberOfTeams.getSelectedItem().toString();
                Random rand = new Random();
                int n = rand.nextInt(NameArray.size());

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
}
