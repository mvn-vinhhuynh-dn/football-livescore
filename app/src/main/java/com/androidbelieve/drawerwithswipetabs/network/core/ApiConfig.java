package com.androidbelieve.drawerwithswipetabs.network.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by asianTech on 7/27/15.
 */
public class ApiConfig {
    SessionStore sessionStore;
    Context mContext;
    String baseUrl;

    public ApiConfig(Builder builder) {
        this.sessionStore = builder.sessionStore;
        this.mContext = builder.mContext;
        this.baseUrl = builder.baseUrl;
    }

    public static Builder builder(Context context){
        return new Builder(context);
    }

    /**
     * buider class
     */
    public static class Builder {
        SessionStore sessionStore;
        Context mContext;
        String baseUrl;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder sessionStore(@Nullable SessionStore sessionStore) {
            this.sessionStore = sessionStore;
            return this;
        }

        public Builder baseUrl(@NonNull String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public ApiConfig build() {
            return new ApiConfig(this);
        }
    }

}
