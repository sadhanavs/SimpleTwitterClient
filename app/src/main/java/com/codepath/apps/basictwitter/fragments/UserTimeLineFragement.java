package com.codepath.apps.basictwitter.fragments;

import android.os.Bundle;

/**
 * Created by sadhanas on 6/30/14.
 */
public class UserTimeLineFragement extends  TweetsListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateUsersTimeLine();
    }
}
