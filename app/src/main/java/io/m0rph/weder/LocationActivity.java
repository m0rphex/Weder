package io.m0rph.weder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class LocationActivity extends AppCompatActivity {
    List<Location> locationList = new ArrayList<Location>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        locationList = (List<Location>) intent.getSerializableExtra("location_list");
        Location location = (Location)intent.getSerializableExtra("location_object");

        Toast.makeText(getApplicationContext(), location.getName(), Toast.LENGTH_SHORT).show();



        //Thingspeak id request if not set
        //Set ID
        //Get and set newest API json to object = modify list item.




    }

    public void writeData() throws IOException {
        String json = new Gson().toJson(locationList);
        FileOutputStream outputStream;
        String filename = "location_data.json";
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(json.getBytes());
        outputStream.close();
    }

}
