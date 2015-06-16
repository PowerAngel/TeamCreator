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


public class AddPlayers extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    private Spinner spinner; //Kanske inte behövs
    ArrayList<Player> PlayersArray = new ArrayList<Player>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        //searchView searchView_players;
        final TextView textView_players = (TextView) this.findViewById(R.id.textView_Players);
        Button btn_AddName = (Button) this.findViewById(R.id.btn_AddName);
        final EditText editText_name = (EditText) this.findViewById(R.id.editText_name);
        final ListView liteView_Players = (ListView) this.findViewById(R.id.listView_Players);

        btn_AddName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_name != null)
                {
                    Player player = new Player(editText_name.getText().toString(), 1);
                    PlayersArray.add(player);
                    //textView_players.append("\n" + player.getName());
                    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PlayersArray);
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
