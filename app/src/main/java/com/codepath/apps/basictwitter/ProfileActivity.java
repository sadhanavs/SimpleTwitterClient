package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.fragments.TweetsFragment;
import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;


public class ProfileActivity extends FragmentActivity {
    private TwitterClient client;
    private Profile profile;
    TextView tvName ;
    TextView tvTagLine;
    TextView tvFollower;
    TextView tvFollowing;
    ImageView ivProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvFollower = (TextView) findViewById(R.id.tvFollower);
        tvName = (TextView) findViewById(R.id.tvName);
        tvTagLine = (TextView) findViewById(R.id.tvTagLine);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        client = TwitterApp.getRestClient();
        profile = new Profile();
        populateProfile();

    }


    public void populateProfile(){
        client.getMyInfo( new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject json){
                Log.d("debug", json.toString());
                profile = Profile.fromJson(json);
                Log.d("debug", profile.toString());
                getActionBar().setTitle("@" + profile.getScreen_name());
                tvName.setText(profile.getScreen_name());
                //tvTagLine.setText(profile.getDescription());
                tvFollower.setText(Integer.toString(profile.getFollowers_count())+ " Followers");
                tvFollowing.setText(Integer.toString(profile.getFollowing())+" Following");
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(profile.getImageUrl(),ivProfileImage);
            }
            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
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
