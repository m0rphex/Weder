package io.m0rph.weder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static io.m0rph.weder.R.id.btn_AddLocation;

public class MainActivity extends AppCompatActivity {


    List<Location> locationList = new ArrayList<Location>();
    ListAdapter locationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationList.add(new Location(1,"Home",1,null));
        locationList.add(new Location(2,"Cottage",1,null));

        locationListAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_expandable_list_item_1, locationList);
        ListView locationListView = (ListView) findViewById(R.id.locationList);
        locationListView.setAdapter(locationListAdapter);

        locationListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                        //intent.putExtra("com.example.myfirstapp.MESSAGE", message);
                        startActivity(intent);
                    }
                }
        );
    }


    public void addLocation(View view) {
        EditText editText = (EditText) findViewById(R.id.txt_addLocation);
        String name = editText.getText().toString();
        locationList.add(new Location(3,name,1,null));
        //locationListAdapter.notify();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
