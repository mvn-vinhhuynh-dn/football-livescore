package com.androidbelieve.footballlivescore.network.apis;

import com.androidbelieve.footballlivescore.models.AgainstPass;
import com.androidbelieve.footballlivescore.models.BXH;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.network.core.ApiClient;
import com.androidbelieve.footballlivescore.network.core.Callback;

import retrofit.RetrofitError;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by tientun on 7/27/15.
 */
public class AuthApi {
    public static void getBxhBundesliga(final Callback<BXH> callback) {
        ApiClient.call().BxhBundesliga(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                callback.success(bxh);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getBxhPrimerLeague(final Callback<BXH> callback) {
        ApiClient.call().BxhPrimerLeague(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                callback.success(bxh);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getBxhLaliga(final Callback<BXH> callback) {
        ApiClient.call().BxhLaliga(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                callback.success(bxh);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getBxhLigue1(final Callback<BXH> callback) {
        ApiClient.call().BxhLigue1(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                callback.success(bxh);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getBxhSeria(final Callback<BXH> callback) {
        ApiClient.call().BxhSeria(new Callback<BXH>() {
            @Override
            public void success(BXH bxh) {
                callback.success(bxh);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getLtdPrimerLueage(final Callback<LTD> callback) {
        ApiClient.call().ltdPrimer(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                callback.success(ltd);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getLtdLaliga(final Callback<LTD> callback) {
        ApiClient.call().ltdLaliga(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                callback.success(ltd);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getLtdBundesliga(final Callback<LTD> callback) {
        ApiClient.call().ltdBundesliga(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                callback.success(ltd);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getLtdSeria(final Callback<LTD> callback) {
        ApiClient.call().ltdSeria(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                callback.success(ltd);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getLtdC1(final Callback<LTD> callback){
        ApiClient.call().ltdC1(new Callback<LTD>() {
            @Override
            public void success(LTD ltd) {
                callback.success(ltd);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }

    public static void getDetailsMatch(String id, final Callback<AgainstPass> callback) {
        ApiClient.call().getDetailsMatch(id, new Callback<AgainstPass>() {
            @Override
            public void success(AgainstPass result) {
                callback.success(result);
            }

            @Override
            public void failure(RetrofitError error, com.androidbelieve.footballlivescore.network.Error myError) {
                callback.failure(error, myError);
            }
        });
    }
}
