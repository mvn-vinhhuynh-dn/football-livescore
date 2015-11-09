package com.androidbelieve.drawerwithswipetabs.models;

import java.io.Serializable;

import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/4/15.
 */
@Data
public class FCInformation implements Serializable{
    private String name;
    private String avatarUrl;
    private String country;
    private String position;
    private String urlInformation;
    private String urlFlagCountry;
}
