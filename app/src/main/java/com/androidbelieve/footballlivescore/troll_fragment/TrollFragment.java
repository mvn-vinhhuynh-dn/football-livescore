package com.androidbelieve.footballlivescore.troll_fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.models.Troll;
import com.androidbelieve.footballlivescore.util.DividerItemDecoration;
import com.androidbelieve.footballlivescore.util.RecyclerItemClickListener;
import com.google.android.gms.analytics.Tracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by phulx on 05/11/2015.
 */
@EFragment(R.layout.troll_fragment)
public class TrollFragment extends BaseFragment {
    @ViewById(R.id.recycleTroll)
    RecyclerView mRecycleTroll;
    @ViewById(R.id.progress_dialog_troll)
    MaterialProgressBar mProgressDialogTroll;

    private ArrayList<Troll> mArraylist;
    private TrollAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;

    private int pastVisiblesItems;
    private int visibleItemCount;
    private int totalItemCount;
    private boolean loading = true;
    private Tracker mTracker;

    @AfterViews
    void afterView() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        mArraylist = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        initViews();
        loadData("http://trollbongda.com");
    }

    public void initViews() {
        mProgressDialogTroll.setVisibility(View.VISIBLE);
        mRecycleTroll.setLayoutManager(linearLayoutManager);
        mRecycleTroll.addItemDecoration(new DividerItemDecoration(getResources()
                .getDrawable(R.drawable.divider)));

        mAdapter = new TrollAdapter(getActivity(), mArraylist);
        mRecycleTroll.setAdapter(mAdapter);
        loadmore();

        mRecycleTroll.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleTroll, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (checkVideoYoutube(mArraylist.get(position).getImg())) {
                    Intent in = new Intent(getActivity(), VideoTrollDialog.class);
                    in.putExtra("url", mArraylist.get(position).getImg().substring(26, 37));
                    getActivity().startActivity(in);

                } else {
                    ImageTrollDialog mDialog = ImageTrollDialog_.builder()
                            .mtroll(mArraylist.get(position))
                            .build();
                    mDialog.show(getFragmentManager(), "Image_Troll");
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    //add Loadmore for RecycleView
    public void loadmore() {

        mRecycleTroll.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // add swipeRefresh listener
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        loading = false;
                        int a = mArraylist.size();
                        loadData("http://trollbongda.com/?start=" + a);
                    }
                }
            }
        });
    }

    @Background
    void loadData(String url) {
        Document document = null;
        Connection con = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2");
//                .timeout(13000);
        try {
            Connection.Response resp = con.ignoreHttpErrors(true).followRedirects(true).execute();
            if (resp.statusCode() == 200) {
                loading = true;
                document = con.get();
                if (document != null) {
                    Element element = document.select("div#itemListSecondary").first();
                    if (element != null) {
                        Elements listDiv = element.select("div[class=panel panel-default itemContainer itemContainerLast]");
                        if (listDiv != null) {
                            for (int i = 0; i < listDiv.size(); i++) {
                                Troll troll = new Troll();
                                if (isAdd(listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString())) {
                                    troll.setImg("http://trollbongda.com" + listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString());
                                    troll.setContent(listDiv.get(i).select("div.catItemBody").first().select("img").attr("alt").toString());
                                } else {
                                    troll.setImg(listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString());
                                    troll.setContent(listDiv.get(i).select("div.catItemBody").first().select("img").attr("alt").toString());
                                }
                                mArraylist.add(troll);
                            }

                            setAdapter();
                        }
                    }
                }
            } else if (resp.statusCode() == 307) {
                loading = true;
                String sUrl = "";
                String sNewUrl = resp.header("Location");
                if (sNewUrl != null && sNewUrl.length() > 7)
                    sUrl = sNewUrl;
                resp = Jsoup.connect(sUrl)
//                        .timeout(13000)
                        .execute();
                document = resp.parse();
                if (document != null) {
                    Element element = document.select("div#itemListSecondary").first();
                    if (element != null) {
                        Elements listDiv = element.select("div[class=panel panel-default itemContainer itemContainerLast]");
                        if (listDiv != null) {
                            for (int i = 0; i < listDiv.size(); i++) {
                                Troll troll = new Troll();
                                if (isAdd(listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString())) {
                                    troll.setImg("http://trollbongda.com" + listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString());
                                    troll.setContent(listDiv.get(i).select("div.catItemBody").first().select("img").attr("alt").toString());
                                } else {
                                    troll.setImg(listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString());
                                    troll.setContent(listDiv.get(i).select("div.catItemBody").first().select("img").attr("alt").toString());
                                }
                                mArraylist.add(troll);
                            }

                            setAdapter();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAdapter();
    }

    private boolean checkVideoYoutube(String url) {
        if (url.startsWith("http://img.youtube.com")) {
            return true;
        }
        return false;
    }

    private boolean isAdd(String url) {
        if (url.startsWith("http://")) {
            return false;
        }
        return true;
    }

    @UiThread
    public void setAdapter() {
        mProgressDialogTroll.setVisibility(View.INVISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment;
        fragment = getFragmentManager().findFragmentByTag("Image_Troll");
        if (fragment != null && fragment instanceof ImageTrollDialog) {
            ((ImageTrollDialog) fragment).onActivityResult(requestCode, resultCode, data);
        }
    }
}
