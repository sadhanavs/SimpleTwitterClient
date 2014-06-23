package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.basictwitter.models.Tweet;


import java.util.ArrayList;


public class TimeLineActivity extends Activity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private ListView lvTweets;
    private Profile profile;

    static final int RESULT_CODE=25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        client = TwitterApp.getRestClient();
        populateTimeLine();
        getUserInfo();
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets  = new ArrayList<Tweet>();
        aTweets = new TweetArrayAdapter(this,tweets);
        lvTweets.setAdapter(aTweets);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_CODE && resultCode == RESULT_OK) {
            aTweets.clear();
            populateTimeLine();
        }
    }

    public void populateTimeLine() {

        client.getHomeTimeLine(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("debug", jsonArray.toString());
                aTweets.addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }

    public  void getUserInfo(){
        client.getUserInfo( new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject json) {
                Log.d("debug", json.toString());
                profile = Profile.fromJson(json);
                Log.d("debug", profile.toString());
                //aTweets.addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_line, menu);
        return true;
    }

    public void onClickWrite(MenuItem menuItem){
        Intent i = new Intent(this,TweetActivity.class);
        i.putExtra("profile",profile);
        startActivityForResult(i,RESULT_CODE);
        //i.putExtra("parameters",parameters);
        //startActivityForResult(i,RESULT_CODE);

    }

    public void onRefresh(MenuItem menuItem) {
         aTweets.clear();
         populateTimeLine();
         //aTweets.notify();
    }
}
