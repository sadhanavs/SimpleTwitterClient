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
    private String description;

    public String getProfile_bkg_image() {
        return profile_bkg_image;
    }

    public int getFollowers_count() {

        return followers_count;
    }

    public int getFollowing() {
        return following;
    }

    private String profile_bkg_image;
    private int followers_count;
    private int following;


    @Override
    public String toString() {
        return  screen_name + " - " + imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public static  Profile fromJson(JSONObject jsonObject) {

        Profile p = new Profile();
        try {

            p.screen_name = jsonObject.getString("name");
            p.imageUrl = jsonObject.getString("profile_image_url_https");
            p.profile_bkg_image = jsonObject.getString("profile_background_image_url_https");
            p.followers_count = jsonObject.getInt("followers_count");
            p.following = jsonObject.getInt("friends_count");
            p.description = jsonObject.getString("description");


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
