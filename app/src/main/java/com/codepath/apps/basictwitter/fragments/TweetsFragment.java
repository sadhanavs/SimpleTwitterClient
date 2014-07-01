package com.codepath.apps.basictwitter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.EndlessScrollListener;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TimeLineActivity;
import com.codepath.apps.basictwitter.TweetArrayAdapter;
import com.codepath.apps.basictwitter.TwitterApp;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Profile;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sadhanas on 6/30/14.
 */
public class TweetsFragment extends Fragment  {
    private Profile profile;
    ImageView ivProfileImage ;
    TextView tvUserName;
    TextView tvTweet;
    EditText etTweet;
    private TwitterClient client;


    //private View.OnClickListener listener;

//    public interface OnClickListener{
//        public void onClick(View V);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        profile = new Profile();
       // populateProfile();
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweet, container,false);
        ivProfileImage = (ImageView) v.findViewById(R.id.imageView);
        tvUserName     = (TextView) v.findViewById(R.id.tvName);
        etTweet        = (EditText) v.findViewById(R.id.etTweet);
        populateProfile();
       return v;
    }

    public void postTweet() {
        client.postTweet( etTweet.getText().toString(),new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject json) {
                Log.d("debug --->>", json.toString());

            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });

    }

    public String getTweet(){
        return etTweet.getText().toString();
    }
    public void populateProfile(){
        client.getMyInfo( new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject json){
                Log.d("debug", json.toString());
                profile = Profile.fromJson(json);
                Log.d("debug", profile.toString());
                //getActionBar().setTitle("@" + profile.getScreen_name());

                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(profile.getImageUrl(),ivProfileImage);


                tvUserName.setText("@"+profile.getScreen_name());

            }
            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }
}
