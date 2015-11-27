package com.androidbelieve.footballlivescore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/26/15.
 */
@Data
public class LTD {

    /**
     * _links : [{"self":"http://api.football-data.org/alpha/soccerseasons/398/fixtures"},{"soccerseason":"http://api.football-data.org/alpha/soccerseasons/398"}]
     * count : 380
     * fixtures : [{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/147075"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/398"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/66"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/73"}},"date":"2015-08-08T11:45:00Z","status":"FINISHED","matchday":1,"homeTeamName":"Manchester United FC","awayTeamName":"Tottenham Hotspur FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":0}}]
     */

    private int count;
    /**
     * self : http://api.football-data.org/alpha/soccerseasons/398/fixtures
     */
    @SerializedName("_links")
    private List<LinksEntity> _links;
    /**
     * _links : {"self":{"href":"http://api.football-data.org/alpha/fixtures/147075"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/398"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/66"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/73"}}
     * date : 2015-08-08T11:45:00Z
     * status : FINISHED
     * matchday : 1
     * homeTeamName : Manchester United FC
     * awayTeamName : Tottenham Hotspur FC
     * result : {"goalsHomeTeam":1,"goalsAwayTeam":0}
     */

    private List<FixturesEntity> fixtures;

    @Data
    public class LinksEntity {
        private String self;
    }

    @Data
    public class FixturesEntity {
        /**
         * self : {"href":"http://api.football-data.org/alpha/fixtures/147075"}
         * soccerseason : {"href":"http://api.football-data.org/alpha/soccerseasons/398"}
         * homeTeam : {"href":"http://api.football-data.org/alpha/teams/66"}
         * awayTeam : {"href":"http://api.football-data.org/alpha/teams/73"}
         */
        private boolean isFavorite = false;

        @SerializedName("_links")
        private LinksEntity _links;
        private String date;
        private String status;
        private int matchday;
        @SerializedName("homeTeamName")
        private String homeTeamName;
        @SerializedName("awayTeamName")
        private String awayTeamName;
        /**
         * goalsHomeTeam : 1
         * goalsAwayTeam : 0
         */

        private ResultEntity result;

        @Data
        public class LinksEntity {
            /**
             * href : http://api.football-data.org/alpha/fixtures/147075
             */

            private SelfEntity self;
            /**
             * href : http://api.football-data.org/alpha/soccerseasons/398
             */

            private SoccerseasonEntity soccerseason;
            /**
             * href : http://api.football-data.org/alpha/teams/66
             */
            @SerializedName("homeTeam")
            private HomeTeamEntity homeTeam;
            /**
             * href : http://api.football-data.org/alpha/teams/73
             */

            private AwayTeamEntity awayTeam;

            @Data
            public class SelfEntity {
                private String href;
            }

            @Data
            public class SoccerseasonEntity {
                private String href;
            }

            @Data
            public class HomeTeamEntity {
                private String href;
            }

            @Data
            public class AwayTeamEntity {
                private String href;
            }
        }

        @Data
        public class ResultEntity {
            @SerializedName("goalsHomeTeam")
            private int goalsHomeTeam;
            @SerializedName("goalsAwayTeam")
            private int goalsAwayTeam;
        }
    }
}
