package com.example.golvmopp.teamcreator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;


public class AddPlayers extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    ArrayList<Player> ChosenArray = new ArrayList<Player>();
    String myLogTag = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        //searchView searchView_players;
        final EditText editText_name = (EditText) this.findViewById(R.id.editText_name);
        final ListView listView_Players = (ListView) this.findViewById(R.id.listView_Players);
        listView_Players.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_Players.setItemsCanFocus(false);

        ImageButton imageButton_add = (ImageButton) this.findViewById(R.id.imageButton_AddName);
        ImageButton imageButton_Done = (ImageButton) this.findViewById(R.id.imageButton_Done);

        final ArrayList<String> NameArray = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, NameArray);
        listView_Players.setAdapter(adapter);

        listView_Players.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try
                {
                    int check = 0;
                    for(int i = 0; i < ChosenArray.size(); i++)
                    {
                        if(ChosenArray.get(i).getName().equals(PlayersArray.get(position).getName()))
                        {
                            ChosenArray.remove(i);
                            check = 1;
                        }
                    }
                    if(check == 0)
                    {
                        ChosenArray.add(PlayersArray.get(position));
                    }

                }
                catch (Exception e)
                {
                    Log.v(myLogTag, "Nu gick något fel" + e);
                }
            }

        });

        imageButton_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (editText_name != null)
                {
                    Player player = new Player(editText_name.getText().toString(), 1);
                    PlayersArray.add(player);
                    NameArray.add(player.getName());
                    listView_Players.setAdapter(adapter);
                    editText_name.setText("");
                }
            }
        });

        imageButton_Done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    //Intent intent = new Intent(getApplicationContext(), CreateTeams.class);
                    //intent.putParcelableArrayListExtra("ChosenArray", ChosenArray);
                    //startActivity(intent);
                }
                catch(Exception e)
                {
                    Log.v(myLogTag, "Nu gick något fel");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_players, menu);
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
