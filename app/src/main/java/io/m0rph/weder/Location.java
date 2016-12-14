package io.m0rph.weder;

/**
 * Created by Sandro on 13.12.2016.
 */

public class Location {
    String name;
    int thingspeak_id;

    public Location(String name, int thingspeak_id) {
        this.name = name;
        this.thingspeak_id = thingspeak_id;
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

    //HTTP GET to https://api.thingspeak.com/channels/CHANNEL_ID/fields/FIELD_ID.json, replacing CHANNEL_ID with the ID of your channel and FIELD_ID with the ID of your field.
    //https://api.thingspeak.com/channels/156985/fields/1&2&3.json?results=2



}
