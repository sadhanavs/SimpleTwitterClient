package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

public class TweetActivity extends Activity {

    ImageView ivProfileImage ;
    TextView tvUserName;
    TextView tvTweet;
    EditText etTweet;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        client = TwitterApp.getRestClient();
        Profile profile = (Profile)getIntent().getSerializableExtra("profile");
        ivProfileImage = (ImageView) findViewById(R.id.imageView);
//        ivProfileImage.setImageResource(android.R.color.transparent);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(profile.getImageUrl(),ivProfileImage);

        tvUserName = (TextView) findViewById(R.id.tvName);
        tvUserName.setText("@"+profile.getScreen_name());
        etTweet = (EditText) findViewById(R.id.etTweet);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweet, menu);
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


    public void onTweet(View view) {

        Toast.makeText(this,etTweet.getText(),Toast.LENGTH_SHORT).show();

        client.postTweet( etTweet.getText().toString(),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject json) {
                Log.d("debug", json.toString());
//                profile = Profile.fromJson(json);
//                Log.d("debug", profile.toString());
                //aTweets.addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });

        Intent i = new Intent(this,TimeLineActivity.class);
        setResult(RESULT_OK,i);
        finish();

        //startActivity(i);

    }

}
