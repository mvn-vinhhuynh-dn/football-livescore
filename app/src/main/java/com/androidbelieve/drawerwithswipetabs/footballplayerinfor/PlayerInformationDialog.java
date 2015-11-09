package com.androidbelieve.drawerwithswipetabs.footballplayerinfor;


import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.models.FCInformation;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/5/15.
 */
@EFragment(R.layout.dialog_player_information)
public class PlayerInformationDialog extends DialogFragment {

    @ViewById(R.id.img_close_pld)
    ImageView mImgClose;

    @ViewById(R.id.img_avata_pld)
    ImageView mImgAvata;

    @ViewById(R.id.img_flag_pld)
    ImageView mImgFlag;

    @ViewById(R.id.tvname_big_pld)
    TextView mtvNameBig;
    @ViewById(R.id.tvName_small_pld)
    TextView mtvNameSmall;

    @ViewById(R.id.tvCountry_pld)
    TextView mtvCountry;

    @ViewById(R.id.tvTeam_pld)
    TextView mtvTeamName;

    @ViewById(R.id.tvPos_pld)
    TextView mtvPos;

    @ViewById(R.id.tvLeg_pld)
    TextView mtvLeg;

    @ViewById(R.id.tvHeight_pld)
    TextView mtvHeight;

    @ViewById(R.id.tvWeight_pld)
    TextView mtvWeight;

    @ViewById(R.id.tvDOB_pld)
    TextView mtvDob;

    @ViewById(R.id.tvPlaceBod_pld)
    TextView mtvPlaceBorn;
    @ViewById(R.id.progress_dialog_pld)
    MaterialProgressBar mDialog;

    @FragmentArg
    FCInformation myData;
    private String nameSmall = "";
    private String country = "";
    private String dob = "";
    private String placeBorn = "";
    private String nameTeam = "";
    private String pos = "";
    private String height = "";
    private String weight = "";
    private String leg = "";
    private String avataUrl = "";
    private String flagUrl = "";

    @AfterViews
    void afterView() {
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            getDialog().setCanceledOnTouchOutside(false);
            getDialog().getWindow().setGravity(Gravity.CENTER);
            getDialog().getWindow()
                    .getAttributes().windowAnimations = R.style.DialogShowAnimation;
        }
        setDataDefault();
        getData(getString(R.string.main_url) + myData.getUrlInformation());
    }

    private void setDataDefault() {
        if (myData != null) {
            mtvNameBig.setText("Cầu thủ: " + myData.getName());
        }
    }

    @Background
    void getData(String url) {
        mDialog.setVisibility(View.VISIBLE);
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
            Element div = doc.select("div.teambox").first();
            Element div_flag = doc.select("div.fixtubar").first();
            //get player informations
            if (div != null) {
                Element child_divs = div.select("tbody").first();
                Elements trChilds = child_divs.select("tr");
                if (trChilds != null) {
                    for (int i = 0; i < trChilds.size(); i++) {
                        nameSmall = (trChilds.get(0).select("h5").text());
                        country = (trChilds.get(1).select("b").text());
                        avataUrl = trChilds.get(1).select("img.tmlogo").attr("src");
                        dob = (trChilds.get(2).select("b").text());
                        placeBorn = (trChilds.get(3).select("b").text());
                        nameTeam = (trChilds.get(4).select("b").text());
                        pos = (trChilds.get(5).select("td").text());
                        height = (trChilds.get(6).select("td").text());
                        weight = (trChilds.get(7).select("td").text());
                        leg = (trChilds.get(8).select("td").text());
                    }
                }
            }
            if (div_flag != null) {
                Element flagChild = div_flag.select("h4.tit").first();
                flagUrl = flagChild.select("img.fxtlgo").attr("src");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateUI();
    }

    @UiThread
    void UpdateUI() {
        mtvNameSmall.setText(nameSmall);
        mtvCountry.setText(country);
        if (avataUrl != null && !avataUrl.equals(""))
            Picasso.with(getActivity()).load(avataUrl).into(mImgAvata);
        mtvDob.setText(dob);
        mtvPlaceBorn.setText(placeBorn);
        mtvTeamName.setText(nameTeam);
        mtvPos.setText(pos);
        mtvHeight.setText(height);
        mtvWeight.setText(weight);
        mtvLeg.setText(leg);
        if (flagUrl != null && !flagUrl.equals(""))
        Picasso.with(getActivity()).load(flagUrl).into(mImgFlag);
        mDialog.setVisibility(View.GONE);
    }

    @Click(R.id.img_close_pld)
    void closeDialog() {
        if (getDialog().isShowing())
            this.dismiss();
    }
}
