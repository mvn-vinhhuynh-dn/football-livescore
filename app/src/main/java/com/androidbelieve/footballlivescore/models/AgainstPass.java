package com.androidbelieve.footballlivescore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/28/15.
 */
@Data
public class AgainstPass {

    /**
     * _links : {"self":{"href":"http://api.football-data.org/alpha/fixtures/146981"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/398"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}}
     * date : 2015-10-31T12:45:00Z
     * status : TIMED
     * matchday : 11
     * homeTeamName : Chelsea FC
     * awayTeamName : Liverpool FC
     * result : {"goalsHomeTeam":-1,"goalsAwayTeam":-1}
     */
    @SerializedName("fixture")
    private FixtureEntity fixture;
    /**
     * count : 10
     * timeFrameStart : 2010-11-07
     * timeFrameEnd : 2015-05-10
     * homeTeamWins : 3
     * awayTeamWins : 4
     * draws : 3
     * lastHomeWinHomeTeam : {"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/132024"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/341"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2013-12-29T16:00:00Z","status":null,"matchday":19,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":2,"goalsAwayTeam":1}}
     * lastWinHomeTeam : {"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/136941"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/354"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2014-11-08T12:45:00Z","status":"FINISHED","matchday":11,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":2}}
     * lastAwayWinAwayTeam : {"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/15362"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/4"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2011-11-19T15:00:00Z","status":null,"matchday":12,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":2}}
     * lastWinAwayTeam : {"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/15617"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/4"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2012-05-08T19:00:00Z","status":null,"matchday":37,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":4,"goalsAwayTeam":1}}
     * fixtures : [{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/136710"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/354"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2015-05-10T15:00:00Z","status":"FINISHED","matchday":36,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":1}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/136941"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/354"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2014-11-08T12:45:00Z","status":"FINISHED","matchday":11,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":2}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/131888"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/341"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2014-04-27T13:05:00Z","status":null,"matchday":36,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":0,"goalsAwayTeam":2}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/132024"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/341"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2013-12-29T16:00:00Z","status":null,"matchday":19,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":2,"goalsAwayTeam":1}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/128934"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/301"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2013-04-20T22:00:00Z","status":null,"matchday":34,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":2,"goalsAwayTeam":2}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/123667"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/301"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2012-11-10T23:00:00Z","status":null,"matchday":11,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":1}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/15617"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/4"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2012-05-08T19:00:00Z","status":null,"matchday":37,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":4,"goalsAwayTeam":1}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/15362"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/4"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2011-11-19T15:00:00Z","status":null,"matchday":12,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":1,"goalsAwayTeam":2}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/64633"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/114"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}},"date":"2011-02-05T23:00:00Z","status":null,"matchday":26,"homeTeamName":"Chelsea FC","awayTeamName":"Liverpool FC","result":{"goalsHomeTeam":0,"goalsAwayTeam":1}},{"_links":{"self":{"href":"http://api.football-data.org/alpha/fixtures/64489"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/114"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/64"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/61"}},"date":"2010-11-06T23:00:00Z","status":null,"matchday":11,"homeTeamName":"Liverpool FC","awayTeamName":"Chelsea FC","result":{"goalsHomeTeam":2,"goalsAwayTeam":0}}]
     */

    private Head2headEntity head2head;

    @Data
    public class FixtureEntity {
        /**
         * self : {"href":"http://api.football-data.org/alpha/fixtures/146981"}
         * soccerseason : {"href":"http://api.football-data.org/alpha/soccerseasons/398"}
         * homeTeam : {"href":"http://api.football-data.org/alpha/teams/61"}
         * awayTeam : {"href":"http://api.football-data.org/alpha/teams/64"}
         */

        private LinksEntity _links;
        private String date;
        private String status;
        private int matchday;
        @SerializedName("homeTeamName")
        private String homeTeamName;
        @SerializedName("awayTeamName")
        private String awayTeamName;
        /**
         * goalsHomeTeam : -1
         * goalsAwayTeam : -1
         */

        private ResultEntity result;

        @Data
        public class LinksEntity {
            /**
             * href : http://api.football-data.org/alpha/fixtures/146981
             */

            private Head2headEntity.FixturesEntity.LinksEntity.SelfEntity self;
            /**
             * href : http://api.football-data.org/alpha/soccerseasons/398
             */

            private Head2headEntity.FixturesEntity.LinksEntity.SoccerseasonEntity soccerseason;
            /**
             * href : http://api.football-data.org/alpha/teams/61
             */
            @SerializedName("homeTeam")
            private Head2headEntity.FixturesEntity.LinksEntity.HomeTeamEntity homeTeam;
            /**
             * href : http://api.football-data.org/alpha/teams/64
             */
            @SerializedName("awayTeam")
            private Head2headEntity.FixturesEntity.LinksEntity.AwayTeamEntity awayTeam;

        }

        @Data
        public class ResultEntity {
            @SerializedName("goalsHomeTeam")
            private int goalsHomeTeam;
            @SerializedName("goalsAwayTeam")
            private int goalsAwayTeam;


        }
    }

    @Data
    public class Head2headEntity {
        private int count;
        @SerializedName("timeFrameStart")
        private String timeFrameStart;
        @SerializedName("timeFrameEnd")
        private String timeFrameEnd;
        ;
        @SerializedName("homeTeamWins")
        private int homeTeamWins;
        @SerializedName("awayTeamWins")
        private int awayTeamWins;
        private int draws;
        /**
         * _links : {"self":{"href":"http://api.football-data.org/alpha/fixtures/136710"},"soccerseason":{"href":"http://api.football-data.org/alpha/soccerseasons/354"},"homeTeam":{"href":"http://api.football-data.org/alpha/teams/61"},"awayTeam":{"href":"http://api.football-data.org/alpha/teams/64"}}
         * date : 2015-05-10T15:00:00Z
         * status : FINISHED
         * matchday : 36
         * homeTeamName : Chelsea FC
         * awayTeamName : Liverpool FC
         * result : {"goalsHomeTeam":1,"goalsAwayTeam":1}
         */

        private List<FixturesEntity> fixtures;

        @Data
        public class FixturesEntity {
            /**
             * self : {"href":"http://api.football-data.org/alpha/fixtures/136710"}
             * soccerseason : {"href":"http://api.football-data.org/alpha/soccerseasons/354"}
             * homeTeam : {"href":"http://api.football-data.org/alpha/teams/61"}
             * awayTeam : {"href":"http://api.football-data.org/alpha/teams/64"}
             */
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
             * goalsAwayTeam : 1
             */

            private ResultEntity result;

            @Data
            public class LinksEntity {
                /**
                 * href : http://api.football-data.org/alpha/fixtures/136710
                 */

                private SelfEntity self;
                /**
                 * href : http://api.football-data.org/alpha/soccerseasons/354
                 */

                private SoccerseasonEntity soccerseason;
                /**
                 * href : http://api.football-data.org/alpha/teams/61
                 */
                @SerializedName("homeTeam")
                private HomeTeamEntity homeTeam;
                /**
                 * href : http://api.football-data.org/alpha/teams/64
                 */
                @SerializedName("awayTeam")
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
}
