package com.example.jactoc.intermoto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.DialogInterface;
import android.widget.EditText;
import android.text.Editable;
import android.content.Intent;


public class MainActivity extends ActionBarActivity {

    private ImageButton searchButton;
    private ImageButton settingsButton;
    private ImageButton recButtonOn;
    private ImageButton recButtonOff;
    private ImageButton recButtonDisabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button on top
        searchButton = (ImageButton) findViewById(R.id.SearchButton);
        settingsButton = (ImageButton) findViewById(R.id.SettingsButton);

        //button rec (on/off)
        recButtonOn = (ImageButton) findViewById(R.id.RecButtonOn);
        recButtonOff = (ImageButton) findViewById(R.id.RecButtonOff);
        recButtonDisabled = (ImageButton) findViewById(R.id.RecButtonDisabled);

        //default rec_on is not visible
        recButtonOn.setVisibility(View.INVISIBLE);
        recButtonOff.setVisibility(View.INVISIBLE);
        recButtonOn.setClickable(false);
        recButtonOff.setClickable(false);
        recButtonDisabled.setClickable(true);

        //listeners
        searchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    openSearch(v);
            }
        });
        settingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings(v);
            }
        });
        recButtonOff.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording(v);
            }
        });
        recButtonOn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording(v);
            }
        });
        recButtonDisabled.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongButton(v);
            }
        });
    }

    private void openSearch(View view){

        //first visibility
        recButtonDisabled.setVisibility(View.INVISIBLE);
        recButtonOff.setVisibility(View.VISIBLE);
        recButtonOn.setVisibility(View.INVISIBLE);
        recButtonOff.setClickable(true);
        recButtonDisabled.setClickable(false);

        Intent intent = new Intent(this, WiFiDirectActivity.class);
        startActivity(intent);
    }
    private void openSettings(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        //title
        alertDialogBuilder.setTitle("Set the device name");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alertDialogBuilder.setView(input);

        //buttons
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Editable value = input.getText();
                //do something with the value
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //canceled
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }


    private void startRecording(View view){

        Toast.makeText(getApplicationContext(), "Tap again to interrupt the transmission",
                Toast.LENGTH_SHORT).show();

        //accord the visibility
        recButtonOff.setVisibility(View.INVISIBLE);
        recButtonOn.setVisibility(View.VISIBLE);
        recButtonOn.setClickable(true);
    }

    private void stopRecording(View view){

        //accord the visibility
        recButtonOff.setVisibility(View.VISIBLE);
        recButtonOn.setVisibility(View.INVISIBLE);
        recButtonOn.setClickable(false);
        recButtonOff.setClickable(true);
    }

    private void wrongButton(View view){

        //just a warning message to the user
        Toast.makeText(getApplicationContext(), "First you have to discover and connect to a user",
                Toast.LENGTH_SHORT).show();
    }
}
