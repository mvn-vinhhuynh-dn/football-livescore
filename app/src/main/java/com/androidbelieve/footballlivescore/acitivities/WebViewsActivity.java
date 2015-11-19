package com.androidbelieve.footballlivescore.acitivities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.util.ObservableWebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by phulx on 30/10/2015.
 */
@EActivity(R.layout.activity_webview)
public class WebViewsActivity extends AppCompatActivity {
    @ViewById(R.id.webview)
    ObservableWebView mWebView;

    @ViewById(R.id.toolbarWebview)
    Toolbar mToolbar;

    @ViewById(R.id.progress_dialog_webviews)
    MaterialProgressBar mProgressBar;

    @Extra("URL")
    String mUrl;

    @AfterViews
    void afterview() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        startWebView(mUrl);
       // loadDocument();

    //    test(mUrl);

    }

    @Background
    public void test(String mUrl) {
        mProgressBar.setVisibility(View.VISIBLE);
        Document document = null;
        try {
            document = Jsoup.connect(mUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document != null) {
            getdata(document);
        }
    }


    private void getdata(Document document) {
        Element element = document.select("div.text-conent").first();
        Elements ps = element.select("p");
        for (int i=3;i<ps.size();i++){
                if (ps.get(i).select("span.img-share").first() != null) {
                    //Element img = ps.get(i).select("img.news-image").first();
                    Element img = ps.get(i).select("img.news-image").first();
                    Log.d("====>img:", img.attr("src"));
                } else {
                    Log.d("====>", ps.get(i).text());
                }
            }

        //setUiApplication(ps.get(5));
    }

    @UiThread
    public void setUiApplication(Element element) {
        mProgressBar.setVisibility(View.INVISIBLE);

        //mWebView.loadData(element.text(),"text/html", "utf-8");
    }


    private void startWebView(String url) {
        mProgressBar.setVisibility(View.VISIBLE);
        mWebView.setWebViewClient(new WebViewClient() {

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {


            }

            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        });

        // Javascript inabled on webview
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */

        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        mWebView.loadUrl(url);
        mWebView.setOnScrollChangedCallback(new ObservableWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int l, int t) {
                Log.d("longhv", "toobar: " + mToolbar.isShown());

                if (t > 40) {
                    mToolbar.animate().translationY(-mToolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
                    mWebView.setLayoutParams(lp);
                    lp.setMargins(0, 0, 0, 0);

                } else {
                    mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                    lp.setMargins(0, mToolbar.getHeight(), 0, 0);
                    mWebView.setLayoutParams(lp);
                }

            }
        });
    }


    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }
}