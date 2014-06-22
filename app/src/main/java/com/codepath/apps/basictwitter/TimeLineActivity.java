package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import com.codepath.apps.basictwitter.models.Tweet;


import java.util.ArrayList;


public class TimeLineActivity extends Activity {

    private TwitterClient client;
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private ListView lvTweets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        client = TwitterApp.getRestClient();
        populateTimeLine();
        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets  = new ArrayList<Tweet>();
        aTweets = new TweetArrayAdapter(this,tweets);
        lvTweets.setAdapter(aTweets);
    }

    public void populateTimeLine() {
        client.getHomeTimeLine(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                //Log.d("debug", jsonArray.toString());
                aTweets.addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }



}
