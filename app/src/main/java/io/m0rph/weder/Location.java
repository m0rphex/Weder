package io.m0rph.weder;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Sandro on 13.12.2016.
 */

public class Location implements Serializable {
    String name;
    int thingspeak_id;
    JSONObject sensor_data;

    public Location(String name, int thingspeak_id, JSONObject sensor_data) {
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

    public JSONObject getSensor_data() {
        return sensor_data;
    }

    public void setSensor_data(JSONObject sensor_data) {
        this.sensor_data = sensor_data;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

