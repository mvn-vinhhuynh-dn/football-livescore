package com.androidbelieve.footballlivescore.network;

import com.androidbelieve.footballlivescore.models.AgainstPass;
import com.androidbelieve.footballlivescore.models.BXH;
import com.androidbelieve.footballlivescore.models.BaseModel;
import com.androidbelieve.footballlivescore.models.LTD;
import com.androidbelieve.footballlivescore.network.core.Callback;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;


/**
 * Copyright © 2015 AsianTech inc.
 * Created by asiantech on 8/10/15.
 */
public interface Api {

    @FormUrlEncoded
    @POST("/createUser")
    void signup(@Field(Parameter.NAME) String name,
                @Field(Parameter.EMAIL) String email,
                @Field(Parameter.PASSWORD) String password,
                Callback<BaseModel> callback);

    @GET("/alpha/soccerseasons/394/leagueTable")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void BxhBundesliga(Callback<BXH> callback);

    @GET("/alpha/soccerseasons/398/leagueTable")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void BxhPrimerLeague(Callback<BXH> callback);

    @GET("/alpha/soccerseasons/399/leagueTable")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void BxhLaliga(Callback<BXH> callback);

    @GET("/alpha/soccerseasons/401/leagueTable")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void BxhSeria(Callback<BXH> callback);

    @GET("/alpha/soccerseasons/396/leagueTable")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void BxhLigue1(Callback<BXH> callback);

    @GET("/alpha/soccerseasons/399/fixtures")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void ltdLaliga(Callback<LTD> callback);

    @GET("/alpha/soccerseasons/398/fixtures")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void ltdPrimer(Callback<LTD> callback);

    @GET("/alpha/soccerseasons/394/fixtures")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void ltdBundesliga(Callback<LTD> callback);

    @GET("/alpha/soccerseasons/401/fixtures")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void ltdSeria(Callback<LTD> callback);

    @GET("/alpha/soccerseasons/405/fixtures")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void ltdC1(Callback<LTD> callback);

    @GET("/{id_match}")
    @Headers("X-Auth-Token:6c2096fdb0e24c9d93b31bf0ac2060c3")
    void getDetailsMatch(@Path("id_match") String id, Callback<AgainstPass> callback);
}
