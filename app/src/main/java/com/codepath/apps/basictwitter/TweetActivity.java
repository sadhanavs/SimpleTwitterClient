package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.fragments.TweetsFragment;
import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

public class TweetActivity extends FragmentActivity {

    TweetsFragment tweetFragment;
    public void onTweet(View view) {

        tweetFragment.postTweet();

           //Toast.makeText(this,tweetFragment.getTweet(),Toast.LENGTH_SHORT).show();
           Intent i = new Intent(this,TimeLineActivity.class);
           startActivity(i);
        //listener.onClick(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        tweetFragment = (TweetsFragment) getSupportFragmentManager().findFragmentById(R.id.frTweet);

     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.tweet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
