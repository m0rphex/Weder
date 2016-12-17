package io.m0rph.weder;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Sandro on 13.12.2016.
 */

public class Location implements Serializable  {
    String name;
    int thingspeak_id;
    String sensor_data;

    public Location(String name, int thingspeak_id, String sensor_data) {
        this.name = name;
        this.thingspeak_id = thingspeak_id;
        this.sensor_data = sensor_data;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getThingspeak_id() {
        return thingspeak_id;
    }
    public void setThingspeak_id(int thingspeak_id) {
        this.thingspeak_id = thingspeak_id;
    }




    public JSONObject getChannel() throws JSONException, MalformedURLException  {
        HttpURLConnection urlConnection = null;
        URL url = new URL("https://api.thingspeak.com/channels/" + this.thingspeak_id + "/fields/1.json?results=1");
        StringBuilder result = new StringBuilder();
        
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        }catch( Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        JSONObject req = new JSONObject(result.toString());
        JSONObject channel = req.getJSONObject("channel");
        return channel;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

