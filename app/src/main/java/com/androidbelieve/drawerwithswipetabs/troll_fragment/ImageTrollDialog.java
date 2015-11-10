package com.androidbelieve.drawerwithswipetabs.troll_fragment;

import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.Troll;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 *
 * Created by phulx on 06/11/2015.
 */
@EFragment(R.layout.dialog_img_troll)
public class ImageTrollDialog extends DialogFragment {
    @ViewById(R.id.imgDetailsTroll)
    ImageView mImageView;

    @FragmentArg
    Troll mtroll;

    @AfterViews
    void afterView(){
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Picasso.with(getActivity()).load(mtroll.getImg()).into(mImageView);
        PhotoViewAttacher mPhotoview = new PhotoViewAttacher(mImageView);

    }
}
