package com.example.golvmopp.teamcreator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AddPlayers extends ActionBarActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    ArrayList<String> NameArray = new ArrayList<String>();
    ArrayList<Player> ChosenArray = new ArrayList<Player>();
    String myLogTag = "MyTag";
    private String FILE_NAME = "Players.txt";
    ListView listView_Players = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        importArrayList();
        buttonDone();
        buttonAdd();
        fixListView();
        //searchView searchView_players;
    }

    public void buttonDone()
    {
        ImageButton imageButton_Done = (ImageButton) this.findViewById(R.id.imageButton_Done);
        imageButton_Done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Intent intent = new Intent(getApplicationContext(), CreateTeams.class);
                    intent.putParcelableArrayListExtra("ChosenArray", ChosenArray);
                    startActivity(intent);
                }
                catch (Exception e)
                {

                    Log.v(myLogTag, "Nu gick n�got fel" + e);

                    Log.v(myLogTag, "Nu gick n�got fel i intenten: " + e);

                }
            }
        });
    }

    public void buttonAdd()
    {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, NameArray);
        final EditText editText_name = (EditText) this.findViewById(R.id.editText_name);
        ImageButton imageButton_add = (ImageButton) this.findViewById(R.id.imageButton_AddName);
        imageButton_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (editText_name != null) {
                    Player player = new Player(editText_name.getText().toString(), 1);
                    PlayersArray.add(player);
                    NameArray.add(player.getName());
                    listView_Players.setAdapter(adapter);
                    editText_name.setText("");
                }
            }
        });
    }

    public void fixListView()
    {
        listView_Players = (ListView) this.findViewById(R.id.listView_Players);
        listView_Players.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_Players.setItemsCanFocus(false);
        listView_Players.setAdapter(adapter);

        listView_Players.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {

                    int check = 0;
                    for (int i = 0; i < ChosenArray.size(); i++)
                    {
                        if (ChosenArray.get(i).getName().equals(PlayersArray.get(position).getName()))
                        {
                            ChosenArray.remove(i);
                            check = 1;
                        }
                    }
                    if (check == 0)
                    {
                        ChosenArray.add(PlayersArray.get(position));
                    }

                }
                catch (Exception e)
                {
                    Log.v(myLogTag, "Nu gick n�got fel" + e);

                    Log.v(myLogTag, "Nu gick n�got fel i markeringen av players: " + e);

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

    protected void onPause()
    {
        super.onPause();
        saveArrayList();
    }

    public void importArrayList()
    {
        PlayersArray = new ArrayList<Player>();
        NameArray = new ArrayList<String>();
        try
        {
            FileInputStream fis = openFileInput(FILE_NAME);
            Scanner scanner = new Scanner(fis);
            String[] tokens;
            while(scanner.hasNext())
            {
                String text = scanner.nextLine();
                tokens = text.split(",");

                Player player = new Player(tokens[0], Double.parseDouble(tokens[1]));
                PlayersArray.add(player);
                NameArray.add(player.getName());
                Log.v(myLogTag, "player.getname: " + player.getName() + " || player.getskill: " + player.getSkill());
            }
            //listView_Players.setAdapter(adapter);
            scanner.close();
        }
        catch(FileNotFoundException e)
        {
            Log.e(myLogTag, e.getMessage(), e);
        }
    }
    public void saveArrayList()
    {
        try
        {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fos);
            String text = null;

            for(int i = 0; i < PlayersArray.size(); i++)
            {
                if(i == 0)
                {
                    text = PlayersArray.get(i).getName() + "," + String.valueOf(PlayersArray.get(i).getSkill() + "\n");
                }
                else
                {
                    text = text + PlayersArray.get(i).getName() + "," + String.valueOf(PlayersArray.get(i).getSkill() + "\n");
                }

            }
            Log.v(myLogTag,"text: " + text);
            pw.println(text);
            pw.close();
        }
        catch(FileNotFoundException e)
        {
            Log.e(myLogTag, e.getMessage(), e);
        }
    }
}
