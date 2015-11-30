package com.androidbelieve.footballlivescore.database;

import com.orm.SugarRecord;

import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/26/15.
 */
@Data
public class FavoriteMatch extends SugarRecord<FavoriteMatch> {
    public String homeName;
    public String awayName;
    public String timeStart;
    public int isNotification;
    public String headerId;

    public FavoriteMatch() {
    }

    public FavoriteMatch(String homeName, String awayName, String timeStart, int isNotification, String headerId) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.timeStart = timeStart;
        this.isNotification = isNotification;
        this.headerId = headerId;
    }
}