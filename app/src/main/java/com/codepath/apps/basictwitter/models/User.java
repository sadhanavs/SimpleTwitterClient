package com.codepath.apps.basictwitter.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sadhanas on 6/21/14.
 */
public class User {

    private String name;
    private long uid;
    private  String screenName;
    private  String profileImageUrl;

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public  static User fromJson(JSONObject jsonObject){
        User u = new User();
        try {
            u.name = jsonObject.getString("name");
           // u.uid = jsonObject.getLong("uid");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl=jsonObject.getString("profile_image_url");

        }catch (JSONException ee){

            ee.printStackTrace();
            return null;
        }

        return u;
    }
}
