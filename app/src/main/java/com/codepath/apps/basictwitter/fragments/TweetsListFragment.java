package com.codepath.apps.basictwitter.fragments;

/**
 * Created by sadhanas on 6/29/14.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basictwitter.EndlessScrollListener;
import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.TweetArrayAdapter;
import com.codepath.apps.basictwitter.TwitterApp;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Profile;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TweetsListFragment extends Fragment{
    private ArrayList<Tweet> tweets;
    private ArrayAdapter<Tweet> aTweets;
    private ListView lvTweets;
    private TwitterClient client;


    public  void getUserInfo(){
//        client.getUserInfo( new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(JSONObject json) {
//                Log.d("debug", json.toString());
//                profile = Profile.fromJson(json);
//                Log.d("debug", profile.toString());
//                //aTweets.addAll(Tweet.fromJsonArray(jsonArray));
//            }
//
//            @Override
//            public void onFailure(Throwable throwable, String s) {
//                Log.d("debug", s.toString());
//            }
//        });
    }

    public void populateHomeTimeLine() {

        client.getHomeTimeLine(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("debug", jsonArray.toString());
                addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }


    public void populateMentionsTimeLine() {

        client.getMentions(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("debug", jsonArray.toString());
                addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }

    public void populateUsersTimeLine() {

        client.getUserTimeLine(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Log.d("debug", jsonArray.toString());
                addAll(Tweet.fromJsonArray(jsonArray));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                Log.d("debug", s.toString());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        tweets  = new ArrayList<Tweet>();
        aTweets = new TweetArrayAdapter(getActivity(),tweets);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, container,false);
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);

        lvTweets.setAdapter(aTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //customLoadMoreDataFromApi();
            }
        });
        return v;
    }



    public void clear(){
        aTweets.clear();
    }
    public void addAll(ArrayList<Tweet> tweets){
        aTweets.addAll(tweets);
    }


}
