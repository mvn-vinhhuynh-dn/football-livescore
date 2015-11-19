package com.androidbelieve.footballlivescore.models;

import lombok.Data;

/**
 *
 * Created by phulx on 11/11/2015.
 */
@Data
public class LtdToday {
    private String nameLeague;
    private String time;
    private String date;
    private String homeName;
    private String imgHome;
    private String imgAway;
    private String awayName;
    private String linkHome;
    private String linkAway;
    private String linkSoccer;
    private int headerId;
    private String typeId;
    private Catran catran;
    private Hiep1 hiep1;


    @Data
    public class Catran {
        private String hdp_rte;
        private String hdp_1;
        private String hdp_2;
        private String oue_rte;
        private String oue_1;
        private String oue_2;
    }
    @Data
    public class Hiep1 {
        private String hdp_rte;
        private String hdp_1;
        private String hdp_2;
        private String oue_rte;
        private String oue_1;
        private String oue_2;
    }

}
