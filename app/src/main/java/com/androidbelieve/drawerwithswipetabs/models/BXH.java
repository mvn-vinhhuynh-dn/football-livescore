package com.androidbelieve.drawerwithswipetabs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by phulx on 23/10/2015.
 */
@Data
public class BXH {
    @SerializedName("_links")
    private LinksEntity _links;
    @SerializedName("leagueCaption")
    private String leagueCaption;
    private int matchday;
    private ArrayList<StandingEntity> standing;
    @Data
    public class LinksEntity {
        private String self;
        private String soccerseason;
    }
    @Data
    public class StandingEntity {
        @SerializedName("_links")
        private LinksEntity _links;
        private int position;
        @SerializedName("teamName")
        private String teamName;
        @SerializedName("playedGames")
        private int playedGames;
        private int points;
        private int goals;
        @SerializedName("goalsAgainst")
        private int goalsAgainst;
        @SerializedName("goalDifference")
        private int goalDifference;
        @Data
        public class LinksEntity {
            /**
             * href : http://api.football-data.org/alpha/teams/5
             */

            private TeamEntity team;
            @Data
            public class TeamEntity {
                private String href;
            }
        }
    }
}
