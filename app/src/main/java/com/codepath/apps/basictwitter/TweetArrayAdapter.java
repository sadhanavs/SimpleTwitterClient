package com.codepath.apps.basictwitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by sadhanas on 6/21/14.
 */
public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context,  List<Tweet> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        View v;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.tweet_text,parent,false);

        }else {
            v = convertView;
        }
        ImageView ivProfileImage = (ImageView)v.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
        TextView tvHandle = (TextView) v.findViewById(R.id.tvHandle);
        ivProfileImage.setImageResource(android.R.color.transparent);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),ivProfileImage);
        tvUserName.setText(tweet.getUser().getName());
        TextView tvTimeCreated = (TextView) v.findViewById(R.id.tvTimeCreated);
        String timeCr = tweet.getCreatedAt();
        tvTimeCreated.setText(tweet.getRelativeTimeAgo());
        tvBody.setText(tweet.getBody());
        tvHandle.setText(" @"+tweet.getUser().getScreenName()); //tweet.getUser().getName()
        return v;

    }
}
