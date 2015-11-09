package com.androidbelieve.drawerwithswipetabs.troll_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.abstracts.BaseFragment;
import com.androidbelieve.drawerwithswipetabs.footballplayerinfor.PlayerInformationDialog;
import com.androidbelieve.drawerwithswipetabs.footballplayerinfor.PlayerInformationDialog_;
import com.androidbelieve.drawerwithswipetabs.models.Troll;
import com.androidbelieve.drawerwithswipetabs.util.DividerItemDecoration;
import com.androidbelieve.drawerwithswipetabs.util.RecyclerItemClickListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
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

    @AfterViews
    void afterView() {
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
                Log.d("=====", mArraylist.get(position).getImg());
                ImageTrollDialog mDialog = ImageTrollDialog_.builder()
                        .mtroll(mArraylist.get(position))
                        .build();
                mDialog.show(getChildFragmentManager(), "aaaa");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
    //add Loadmore for RecycleView
    public void loadmore(){

        mRecycleTroll.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // add swipeRefresh listener
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if ( loading ) {
                    if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        loading = false;
                        int a = mArraylist.size();
                        loadData("http://trollbongda.com/?start="+a);
                    }
                }
            }
        });
    }

    @Background
    void loadData(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document != null) {
            loading =true;
            getdata(document);
        }
    }

    private boolean isAdd(String url) {
        if (url.startsWith("http://")) {
            return false;
        }
        return true;
    }

    private void getdata(Document document) {
        Element element = document.select("div#itemListSecondary").first();
        if (element != null) {
            Elements listDiv = element.select("div[class=panel panel-default itemContainer itemContainerLast]");
            if (listDiv != null) {
                for (int i = 0; i < listDiv.size(); i++) {
                    Troll troll = new Troll();
                        if (isAdd(listDiv.get(i).select("div.catItemBody").first().select("img").attr("src").toString())){
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

    @UiThread
    public void setAdapter() {
        mProgressDialogTroll.setVisibility(View.INVISIBLE);
        mAdapter.notifyDataSetChanged();
    }
}
