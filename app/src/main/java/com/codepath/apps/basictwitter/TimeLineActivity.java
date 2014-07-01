package com.codepath.apps.basictwitter;
import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Locale;

import com.codepath.apps.basictwitter.fragments.HomeTimeLineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimeLineFragment;
import com.codepath.apps.basictwitter.fragments.TweetsListFragment;
import com.codepath.apps.basictwitter.listeners.FragmentTabListener;
import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import android.text.format.DateUtils;
import java.text.ParseException;
import com.codepath.apps.basictwitter.models.Tweet;


import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class TimeLineActivity extends FragmentActivity {

    static final int RESULT_CODE=25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        setupTabs();
   }
    public void onProfileView(MenuItem mi){
        Intent i = new Intent(this,ProfileActivity.class);
        startActivity(i);

    }

    private void setupTabs() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tab1 = actionBar
                .newTab()
                .setText("Home")
                .setIcon(R.drawable.ic_home)
                .setTag("HomeTimelineFragment")
                .setTabListener(
                        new FragmentTabListener<HomeTimeLineFragment>(R.id.flContainer, this, "first",
                                HomeTimeLineFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        ActionBar.Tab tab2 = actionBar
                .newTab()
                .setText("Mention")
                .setIcon(R.drawable.ic_mentions)
                .setTag("MentionsTimelineFragment")
                .setTabListener(
                        new FragmentTabListener<MentionsTimeLineFragment>(R.id.flContainer, this, "second",
                                MentionsTimeLineFragment.class));

        actionBar.addTab(tab2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_CODE && resultCode == RESULT_OK) {
            //fragmentTweetsList.clear();
            //populateTimeLine();
        }
    }


    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_line, menu);
        return true;
    }

    public void onClickWrite(MenuItem menuItem){
        Intent i = new Intent(this,TweetActivity.class);
        startActivity(i);
     }

    public void onRefresh(MenuItem menuItem) {
        //fragmentTweetsList.clear();
         //populateTimeLine();
         //aTweets.notify();
    }

    public void customLoadMoreDataFromApi() {
/*
        if (client.getCount() > 200) {
            client.setCount(0);
            fragmentTweetsList.clear();
        }else {
            client.incrementCount();
        }
        populateTimeLine();
*/
    }

}
