package com.example.christian.maptest;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CallActivity2 extends AppCompatActivity {

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);// Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);// Setting toolbar as the ActionBar with setSupportActionBar() call

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
       // navigationView.setNavigationItemSelectedListener(this);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Tierspital der Veterinärmedizinischen Universität Wien",
                "Tierklinik Aspern",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "List View Array Adapter",
                "Android Example List View"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                //Zeige String
                //String s = listView.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();


                switch (position) {
                    case 0:
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:123456789"));
                        startActivity(callIntent);
                        break;
                    case 1:
                        Intent callIntent2 = new Intent(Intent.ACTION_CALL);
                        callIntent2.setData(Uri.parse("tel:11111111"));
                        startActivity(callIntent2);
                        break;
                    case 2:
                        Intent newActivity2 = new Intent(CallActivity2.this, CallActivity.class);
                        startActivity(newActivity2);
                        break;



                }
            }

        });



    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Drücke erneut um Hundezone zu beenden", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
