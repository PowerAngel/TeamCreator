package com.example.golvmopp.teamcreator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AddPlayers extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private ArrayAdapter<String> adapter;
    ArrayList<Player> PlayersArray = new ArrayList<Player>();
    ArrayList<String> NameArray = new ArrayList<String>();
    ArrayList<Player> ChosenArray = new ArrayList<Player>();
    String myLogTag = "MyTag";
    private String FILE_NAME = "Players.txt";
    ListView listView_Players = null;
    SearchView searchView_players;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        importArrayList();
        buttonDone();
        buttonAdd();
        buttonRemove();
        fixSearchView();
        fixListView();
    }

    public void buttonDone()
    {
        ImageButton imageButton_Done = (ImageButton) this.findViewById(R.id.imageButton_Done);
        imageButton_Done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(ChosenArray.size() > 0)
                {
                    try
                    {
                        Intent intent = new Intent(getApplicationContext(), CreateTeams.class);
                        intent.putParcelableArrayListExtra("ChosenArray", ChosenArray);
                        startActivity(intent);
                    }
                    catch (Exception e)
                    {
                        Log.v(myLogTag, "Nu gick n�got fel i intenten: " + e);
                    }
                }
                else
                {
                    new AlertDialog.Builder(AddPlayers.this)
                            .setTitle("No player selected")
                            .setMessage("You have to select at least one player to continue")
                            .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {

                                }
                            }).show();
                }
            }
        });
    }

    public void buttonAdd()
    {
        final EditText editText_name = (EditText) this.findViewById(R.id.editText_name);
        ImageButton imageButton_add = (ImageButton) this.findViewById(R.id.imageButton_AddName);
        imageButton_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (editText_name.getText().length() != 0)
                {
                    int check = 0;

                    for(int i = 0; i < PlayersArray.size(); i++)
                    {
                        if(PlayersArray.get(i).getName().equals(editText_name.getText().toString()))
                        {
                            check = 1;
                        }
                    }

                    if(check == 0)
                    {
                        Player player = new Player(editText_name.getText().toString(), 1);
                        PlayersArray.add(player);
                        NameArray.add(player.getName());
                        ((BaseAdapter) listView_Players.getAdapter()).notifyDataSetChanged();
                        editText_name.setText("");
                    }
                    else
                    {
                        editText_name.setText("");
                        Log.v(myLogTag, "Namnet finns redan");
                    }
                }
                else
                {
                    Log.v(myLogTag, "Inget namn ifyllt");
                }
            }
        });
    }

    public void buttonRemove()
    {
        ImageButton imageButton_remove = (ImageButton) this.findViewById(R.id.imageButton_remove);
        imageButton_remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (int i = 0; i < ChosenArray.size(); i++)
                {
                    for (int k = 0; k < PlayersArray.size(); k++)
                    {
                        if(ChosenArray.get(i).getName().equals(PlayersArray.get(k).getName()))
                        {
                            PlayersArray.remove(k);
                            int removeName = -1;
                            for (int j = 0; j < NameArray.size(); j++)
                            {
                                if(NameArray.get(j).toString().equals(ChosenArray.get(i).getName()))
                                {
                                    removeName = j;
                                }
                            }
                            if(removeName != -1)
                            {
                                NameArray.remove(removeName);
                            }
                        }
                    }
                }
                ChosenArray = new ArrayList<Player>();
                ((BaseAdapter) listView_Players.getAdapter()).notifyDataSetChanged();
                for(int i = 0; i < listView_Players.getCount(); i++)
                {
                    listView_Players.setItemChecked(i, false);
                }

            }
        });
    }

    public void fixSearchView()
    {
        searchView_players = (SearchView) this.findViewById(R.id.searchView_players);
        searchView_players.setIconifiedByDefault(false);
        searchView_players.setOnQueryTextListener(this);
        searchView_players.setSubmitButtonEnabled(true);
        searchView_players.setQueryHint("Search here");

    }
    public void fixListView()
    {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, NameArray);
        listView_Players = (ListView) this.findViewById(R.id.listView_Players);
        listView_Players.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView_Players.setItemsCanFocus(false);
        listView_Players.setTextFilterEnabled(true);
        listView_Players.setAdapter(adapter);

        for(int i = 0; i < listView_Players.getCount(); i++)
        {
            listView_Players.setItemChecked(i, false);
        }

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
                if(text != null)
                {
                    tokens = text.split(",");
                    Log.v(myLogTag, "text: " + text);
                    Log.v(myLogTag,"tokens[0]: " + tokens[0] + " tokens[1]: " + tokens[1]);

                    Player player = new Player(tokens[0], Double.parseDouble(tokens[1]));
                    PlayersArray.add(player);
                    NameArray.add(player.getName());
                    Log.v(myLogTag, "player.getname: " + player.getName() + " || player.getskill: " + player.getSkill());
                }
            }
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

    @Override
    public boolean onQueryTextSubmit(String query)
    {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        if(TextUtils.isEmpty(newText))
        {
            listView_Players.clearTextFilter();
        }
        else
        {
            listView_Players.setFilterText(newText.toString());
        }
        return true;
    }
}
