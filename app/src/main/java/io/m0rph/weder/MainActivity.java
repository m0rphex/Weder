package io.m0rph.weder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Location> locationList;
    ListAdapter locationListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (readData().equals("")){
            locationList = new ArrayList<Location>();
        }

        else {
            //locationList.add(new Location(3,readData(),1,null));
            Gson gson = new Gson();
            locationList = gson.fromJson(readData(), new TypeToken<List<Location>>(){}.getType());
        }

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


    public void addLocation(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.txt_addLocation);
        String name = editText.getText().toString();
        locationList.add(new Location(name,1,null));

        String json = new Gson().toJson(locationList);
        FileOutputStream outputStream;
        String filename = "location_data.json";
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(json.getBytes());
        outputStream.close();

        view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String readData(){
        FileInputStream fis = null;
        try {
            fis = openFileInput("location_data.json");
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            return "";
        }
        StringBuffer fileContent = new StringBuffer("");
        byte[] buffer = new byte[1024];
        int n;
        try {
            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "";
        }
        return fileContent.toString();
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
