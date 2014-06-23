package com.codepath.apps.basictwitter.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sadhanas on 6/22/14.
 */
public class Profile implements Serializable{
    private static final long serialVersionId=3423545667123455L;
    private String screen_name;
    private String imageUrl;


    @Override
    public String toString() {
        return  screen_name + " - " + imageUrl;
    }

    public static  Profile fromJson(JSONObject jsonObject) {

        Profile p = new Profile();
        try {

            p.screen_name = jsonObject.getString("name");
            p.imageUrl = jsonObject.getString("profile_image_url_https");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return p;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
