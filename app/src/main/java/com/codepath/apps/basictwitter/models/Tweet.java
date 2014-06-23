package com.codepath.apps.basictwitter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sadhanas on 6/21/14.
 */

public class Tweet {
    private String body;
    private long uid;
    private String createdAt;
    private User user;

    @Override
    public String toString() {
       // return super.toString();

        return getBody()+ " - " + getUser().getScreenName();
    }

    public static ArrayList<Tweet> fromJsonArray(JSONArray json){
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(json.length());
        for( int i =0;i<json.length();i++){
            JSONObject tweetJson = null;
            try {
                tweetJson = json.getJSONObject(i);
            }catch (JSONException e){
                e.printStackTrace();
                continue;
            }
            Tweet tweet = Tweet.fromJson(tweetJson);
            if(tweet != null) {
                tweets.add(tweet);
            }

        }
        return  tweets;
    }

    public static Tweet fromJson(JSONObject jsonObject) {

         Tweet tweet = new Tweet();
         try {

             tweet.body = jsonObject.getString("text");
             tweet.uid = jsonObject.getLong("id");
             tweet.createdAt = jsonObject.getString("created_at");
             tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
         } catch (JSONException e) {
             e.printStackTrace();
             return null;
         }
         return tweet;

    }


    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}
