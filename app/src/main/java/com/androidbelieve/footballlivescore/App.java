package com.androidbelieve.footballlivescore;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.androidbelieve.footballlivescore.network.MySession;
import com.androidbelieve.footballlivescore.network.core.ApiClient;
import com.androidbelieve.footballlivescore.network.core.ApiConfig;

import java.util.Locale;

/**
 * Created by phulx on 23/10/2015.
 */
public class App extends Application {
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale("vn");
        res.updateConfiguration(conf, dm);

        ApiConfig apiConfig = ApiConfig.builder(getApplicationContext())
                .baseUrl(getResources().getString(R.string.base_url))
                .sessionStore(new MySession())
                .build();
        ApiClient.getInstance().init(apiConfig);
        //Test
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }
}
