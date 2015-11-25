package com.androidbelieve.footballlivescore.news_fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidbelieve.footballlivescore.App;
import com.androidbelieve.footballlivescore.R;
import com.androidbelieve.footballlivescore.abstracts.BaseFragment;
import com.androidbelieve.footballlivescore.acitivities.WebViewsActivity_;
import com.androidbelieve.footballlivescore.adapter.NewsAdapter;
import com.androidbelieve.footballlivescore.models.News;
import com.androidbelieve.footballlivescore.util.DividerItemDecoration;
import com.androidbelieve.footballlivescore.util.RecyclerItemClickListener;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by phulx on 29/10/2015.
 */
@EFragment(R.layout.news_tabs_fragment)
public class NewsTabsFragment extends BaseFragment {
    private List title;
    private List description;
    private List links;
    private List pubDate;
    private List summaryImg;
    private List<News> mArrayListNews;
    private List<News> mArraylistNewsHeader;
    private NewsAdapter mAdapterNews;
    @ViewById(R.id.recycleNews)
    RecyclerView mRecycleNews;
    @ViewById(R.id.progress_dialog_news)
    MaterialProgressBar mProgressDialogNews;
    private Tracker mTracker;

    public NewsTabsFragment() {
        title = new ArrayList();
        links = new ArrayList();
        description = new ArrayList();
        pubDate = new ArrayList();
        summaryImg = new ArrayList();
        mArrayListNews = new ArrayList<>();
        mArraylistNewsHeader = new ArrayList<>();
    }

    @AfterViews
    void afterViews() {
        App application = (App) getActivity().getApplication();
        mTracker = application.getDefaultTracker();
        initViews();
        getData();
    }

    public void initViews() {
        mRecycleNews.setLayoutManager(new LinearLayoutManager(getActivity()
                .getBaseContext()));
        mRecycleNews.addItemDecoration(new DividerItemDecoration(getResources()
                .getDrawable(R.drawable.divider)));
        mRecycleNews.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), mRecycleNews, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    //Toast.makeText(getActivity(),"chua lam",Toast.LENGTH_SHORT).show();
                } else {
                    WebViewsActivity_.intent(getActivity())
                            .extra("URL", mArrayListNews.get(position - 1).getLink())
                            .start();
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    @Background
    void getData() {
        try {
            URL url = new URL("http://www.24h.com.vn/upload/rss/bongda.rss");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getInputStream(url), "UTF_8");

            boolean insideItem = false;

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {


                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem) {
                            title.add(xpp.nextText());
                        }

                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem) {
                            links.add(xpp.nextText());
                        }

                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (insideItem) {
                            description.add(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                        if (insideItem) {
                            pubDate.add(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("summaryImg")) {
                        if (insideItem) {
                            summaryImg.add(xpp.nextText());
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    insideItem = false;
                }
                eventType = xpp.next(); //move to next element
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < links.size(); i++) {
            News news = new News();
            news.setTitle(title.get(i).toString());
            news.setLink(links.get(i).toString());
            news.setDescription(description.get(i).toString());
            news.setPubDate(pubDate.get(i).toString());
            news.setSummaryImg(summaryImg.get(i).toString());
            mArrayListNews.add(news);
        }
        setUiApplication();
    }

    @UiThread
    void setUiApplication() {
        mProgressDialogNews.setVisibility(View.INVISIBLE);
        if (mArrayListNews.size() > 5) {
            for (int i = 0; i < 5; i++) {
                mArraylistNewsHeader.add(mArrayListNews.get(i));
            }
        } else {
            mArraylistNewsHeader = mArrayListNews;
        }
        mAdapterNews = new NewsAdapter(getActivity(), mArrayListNews, mArraylistNewsHeader);
        mRecycleNews.setAdapter(mAdapterNews);
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mTracker.setScreenName("News-Screen");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
