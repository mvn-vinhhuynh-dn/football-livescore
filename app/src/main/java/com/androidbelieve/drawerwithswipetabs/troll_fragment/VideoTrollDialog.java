package com.androidbelieve.drawerwithswipetabs.troll_fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.DialogFragment;
import android.util.Config;

import com.androidbelieve.drawerwithswipetabs.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

/**
 * Created by phulx on 09/11/2015.
 */
@EFragment(R.layout.dialog_video_troll)
public class VideoTrollDialog extends DialogFragment implements
        YouTubePlayer.OnInitializedListener {
    @FragmentArg
    String url;
    @ViewById(R.id.youtube_view)
    YouTubePlayerView youTubeView;

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @AfterViews
    void afterView(){
        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    public class Config {
        // Google Console APIs developer key
        // Replace this key with your's
        public static final String DEVELOPER_KEY = "AIzaSyABYoczeHg4XABx_jMRfv-CqmA2YMsIY4A";

        // YouTube video id
        public static final String YOUTUBE_VIDEO_CODE = "_oEA18Y8gM0";
    }
}
