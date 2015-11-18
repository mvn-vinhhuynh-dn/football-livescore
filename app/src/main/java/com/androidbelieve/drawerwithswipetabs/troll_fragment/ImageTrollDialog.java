package com.androidbelieve.drawerwithswipetabs.troll_fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.Troll;
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
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

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
    private static final String PERMISSION = "publish_actions";
    @ViewById(R.id.imgDetailsTroll)
    ImageView mImageView;

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

    @AfterViews
    void afterView() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
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
        login();
    }

    private void postPhoto() {
        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_chelsea);
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
                        postPhoto();
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
}