package com.androidbelieve.footballlivescore.troll_fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.models.Troll;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by phulx on 06/11/2015.
 */
@EFragment(R.layout.dialog_img_troll)
public class ImageTrollDialog extends DialogFragment {
    private ShareDialog shareDialog;
    private CallbackManager mCallbackManager;
    private boolean canPresentShareDialogWithPhotos;
    private Bitmap mBitmap;
    @ViewById(R.id.imgDetailsTroll)
    ImageView mImageView;
    private Tracker mTracker;
    @FragmentArg
    Troll mtroll;
    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("vinhhlb", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("vinhhlb", String.format("Error: %s", error.toString()));
            String title = "Error!";
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("vinhhlb", "Success!");
            if (result.getPostId() != null) {
                String title = "Success";
                String id = result.getPostId();
                String alertMessage = "Posted" + id;
                showResult(title, alertMessage);
            }
        }

        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton("Ok", null)
                    .show();
        }
    };

    @Background
    void getBitmapFromUrl(String url) {
        try {
            mBitmap = null;
            URL myUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            mBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        login();
    }


    @AfterViews
    void afterView() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();

        mCallbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(
                mCallbackManager,
                shareCallback);

        if (mtroll != null)
            Picasso.with(getActivity()).load(mtroll.getImg()).into(mImageView);
        PhotoViewAttacher mPhotoview = new PhotoViewAttacher(mImageView);
    }

    @Click(R.id.img_share_facebook)
    void shareFacebook() {
        getBitmapFromUrl(mtroll.getImg());
    }

    private void postPhoto(Bitmap image) {
        SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap(image).build();
        ArrayList<SharePhoto> photos = new ArrayList<>();
        photos.add(sharePhoto);
        SharePhotoContent sharePhotoContent =
                new SharePhotoContent.Builder().setPhotos(photos).build();
        if (canPresentShareDialogWithPhotos) {
            shareDialog.show(sharePhotoContent);
        } else if (hasPublishPermission()) {
            ShareApi.share(sharePhotoContent, shareCallback);
        }
        Toast.makeText(getActivity(), "Share Sucessfull!", Toast.LENGTH_SHORT).show();
    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

    private void login() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_friends"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.d("vinhhlb", "onSuccess ");
                        if (mBitmap != null) {
                            postPhoto(mBitmap);
                            Log.d("vinhhlb", mBitmap.toString());
                        } else {
                            Log.d("vinhhlb", "Post faild b.c null Bitmap");
                        }
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.d("vinhhlb", "onCancel ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.d("vinhhlb", "onError " + exception.getMessage());
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("Troll-Image-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}