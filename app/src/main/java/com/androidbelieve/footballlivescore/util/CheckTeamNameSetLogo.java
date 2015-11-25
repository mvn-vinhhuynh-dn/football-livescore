package com.androidbelieve.footballlivescore.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.footballlivescore.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 10/23/15.
 */
public class CheckTeamNameSetLogo {

    public static void setTeamName(Context context, TextView textView, String name) {
        if (name.equals("Manchester United FC") || name.equals("Chelsea FC")
                || name.equals("Manchester City FC") || name.equals("Arsenal FC")
                || name.equals("Liverpool FC") || name.equals("Real Madrid CF")
                || name.equals("FC Barcelona") || name.equals("Club Atlético de Madrid")
                || name.equals("Paris Saint-Germain") || name.equals("Borussia Dortmund")
                || name.equals("FC Bayern München") || name.equals("AS Roma")
                || name.equals("AC Milan") || name.equals("FC Internazionale Milano")
                || name.equals("Juventus Turin")) {
            textView.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.dark));
        }
    }

    public static void checkTeamWin(Context context, int isWin, TextView textViewHome, TextView textViewAgains) {
        if (isWin == 0) {
            textViewHome.setTextColor(context.getResources().getColor(R.color.red));
            textViewAgains.setTextColor(context.getResources().getColor(R.color.dark));
        } else if (isWin == 1) {
            textViewHome.setTextColor(context.getResources().getColor(R.color.dark));
            textViewAgains.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            textViewHome.setTextColor(context.getResources().getColor(R.color.dark));
            textViewAgains.setTextColor(context.getResources().getColor(R.color.dark));
        }
    }

    public static void setLogo(Context mContext, ImageView imageView, String name) {
        switch (name) {
            //Primer League
            case "Manchester United FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_manchester_united));
                break;
            case "AFC Bournemouth":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bournemouth));
                break;
            case "Tottenham Hotspur FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_tottenham));
                break;
            case "Everton FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_everton));
                break;
            case "Watford FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_watford));
                break;
            case "Leicester City FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_leicester));
                break;
            case "Sunderland AFC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sunderland));
                break;
            case "Norwich City FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_norwich));
                break;
            case "Crystal Palace FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_crystal_palace));
                break;
            case "Chelsea FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_chelsea));
                break;
            case "Swansea City FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_swansea));
                break;
            case "Newcastle United FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_newcastle));
                break;
            case "Southampton FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_southampton));
                break;
            case "Arsenal FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_arsenal));
                break;
            case "West Ham United FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_west_ham));
                break;
            case "Stoke City FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_stoke));
                break;
            case "Liverpool FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_liverpool));
                break;
            case "West Bromwich Albion FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_west_bromwich));
                break;
            case "Manchester City FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_manchester_city));
                break;
            case "Aston Villa FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_aston));
                break;

            //Laliga
            case "Real Madrid CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_real_madrid));
                break;
            case "RC Celta de Vigo":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_celta_de_vigo));
                break;
            case "FC Barcelona":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_barcelona));
                break;
            case "Club Atlético de Madrid":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_atletico_madrid));
                break;
            case "Villarreal CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_villareal));
                break;
            case "RC Deportivo La Coruna":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_deportivo));
                break;
            case "SD Eibar":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_eibar));
                break;
            case "Valencia CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_valencia));
                break;
            case "RCD Espanyol":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_espanyol));
                break;
            case "Real Betis":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_real_betis));
                break;
            case "Getafe CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_getafe));
                break;
            case "Sporting Gijón":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sporting));
                break;
            case "Sevilla FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sevilla));
                break;
            case "Athletic Club":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_athletic_bilbao));
                break;
            case "Rayo Vallecano de Madrid":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_rayo_vallecano));
                break;
            case "Real Sociedad de Fútbol":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sociedad));
                break;
            case "Málaga CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_malaga));
                break;
            case "Levante UD":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_levante));
                break;
            case "UD Las Palmas":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_las_palmas));
                break;
            case "Granada CF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_granada));
                break;

            //Bundesliga
            case "FC Bayern München":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bayern_munich));
                break;
            case "Borussia Dortmund":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dortmund));
                break;
            case "FC Schalke 04":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_schalke));
                break;
            case "VfL Wolfsburg":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wolfsburg));
                break;
            case "Hertha BSC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hertha));
                break;
            case "1. FC Köln":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_koln));
                break;
            case "Bayer Leverkusen":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_leverkusen));
                break;
            case "FC Ingolstadt 04":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ingolstadt));
                break;
            case "Hamburger SV":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hamburger));
                break;
            case "SV Darmstadt 98":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_darm));
                break;
            case "Bor. Mönchengladbach":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_monchengladbach));
                break;
            case "1. FSV Mainz 05":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mainz_05));
                break;
            case "Eintracht Frankfurt":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_frankfurt));
                break;
            case "Hannover 96":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hannover));
                break;
            case "VfB Stuttgart":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_stuttgart));
                break;
            case "Werder Bremen":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_werder_bremen));
                break;
            case "TSG 1899 Hoffenheim":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hoffenheim));
                break;
            case "FC Augsburg":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_augsburg));
                break;

            //Seria
            case "ACF Fiorentina":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fiorentina));
                break;
            case "AS Roma":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_as_roma));
                break;
            case "FC Internazionale Milano":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_inter));
                break;
            case "SSC Napoli":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_napoli));
                break;
            case "US Sassuolo Calcio":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sassuolo));
                break;
            case "SS Lazio":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_lazio));
                break;
            case "Torino FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_torino));
                break;
            case "Atalanta BC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_atalanta));
                break;
            case "AC Chievo Verona":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_chievo_verona));
                break;
            case "UC Sampdoria":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sampdoria));
                break;
            case "US Cittá di Palermo":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_palermo));
                break;
            case "Genoa CFC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_genoa));
                break;
            case "AC Milan":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ac_milan));
                break;
            case "Juventus Turin":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_juventus));
                break;
            case "Udinese Calcio":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_udinese));
                break;
            case "Frosinone Calcio":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_frosinone));
                break;
            case "Empoli FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_empoli));
                break;
            case "Hellas Verona FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_hellas_verona));
                break;
            case "Carpi FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_carp));
                break;
            case "Bologna FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bologna));
                break;

            //Ligue 1
            case "Paris Saint-Germain":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_psg));
                break;
            case "Angers SCO":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_angers));
                break;
            case "SM Caen":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_caen));
                break;
            case "AS Saint-Étienne":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_saint_etienne));
                break;
            case "OGC Nice":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_nice));
                break;
            case "Olympique Lyonnais":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_lyon));
                break;
            case "Stade Rennais FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_rennais));
                break;
            case "Stade de Reims":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_stade_de_reims));
                break;
            case "FC Lorient":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_lorient));
                break;
            case "AS Monaco FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_monaco));
                break;
            case "EA Guingamp":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_guingamp));
                break;
            case "FC Nantes":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_nantes));
                break;
            case "OSC Lille":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_lille));
                break;
            case "FC Girondins de Bordeaux":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bordeaux));
                break;
            case "SC Bastia":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bastia));
                break;
            case "Olympique de Marseille":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_marseille));
                break;
            case "Toulouse FC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_toulouse));
                break;
            case "Montpellier Hérault SC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_montpellier));
                break;
            case "ES Troyes AC":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_troyes));
                break;
            case "Gazélec Ajaccio":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_ajaccio));
                break;
            //c1-----------------
            case "PSV Eindhoven":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_psv_eindhoven));
                break;
            case "CSKA Moscow":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_cska_moscow));
                break;
            case "Shakhtar Donetsk":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_shakhtar_donetsk));
                break;
            case "Malmö FF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_malmo));
                break;
            case "Galatasaray SK":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_galatasaray));
                break;
            case "Benfica Lissabon":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_benfica));
                break;
            case "FC Astana":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_astana));
                break;
            case "Olympiacos F.C.":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_olympiakos));
                break;
            case "KAA Gent":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_kaa_gent));
                break;
            case "FC Zenit St. Petersburg":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_zenit_saint_petersburg));
                break;
            case "FK BATE Baryssau":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bate_baryssau));
                break;
            case "Maccabi Tel Aviv":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_maccabi_tel));
                break;
            case "Dynamo Kyiv":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_kyiv));
                break;
            case "FC Porto":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_porto));
                break;
            case "GNK Dinamo Zagreb":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dinamo_zagreb));
                break;
        }
    }

    public static String getUrl(Context mContext, String name) {

        switch (name) {
            //Primer League
            case "Manchester United FC":
                return InformationFootBallClub.MAN_UNITED;
            case "AFC Bournemouth":
                return InformationFootBallClub.BOUNER_MOUTH;
            case "Tottenham Hotspur FC":
                return InformationFootBallClub.TOTTEN_HAM;
            case "Everton FC":
                return InformationFootBallClub.EVETON;
            case "Watford FC":
                return InformationFootBallClub.WAT_FORD;
            case "Leicester City FC":
                return InformationFootBallClub.LEI_CESTER_CITY;
            case "Sunderland AFC":
                return InformationFootBallClub.SUNDER_LAND;
            case "Norwich City FC":
                return InformationFootBallClub.NOR_WICH;
            case "Crystal Palace FC":
                return InformationFootBallClub.CRYSCLE_PLACE;
            case "Chelsea FC":
                return InformationFootBallClub.CHELSEA;
            case "Swansea City FC":
                return InformationFootBallClub.SWANSEA_CITY;
            case "Newcastle United FC":
                return InformationFootBallClub.NEWCASTLE;
            case "Southampton FC":
                return InformationFootBallClub.SOUT_HAMPTON;
            case "Arsenal FC":
                return InformationFootBallClub.ARSERAL;
            case "West Ham United FC":
                return InformationFootBallClub.WEST_HAM;
            case "Stoke City FC":
                return InformationFootBallClub.STOCK_CITY;
            case "Liverpool FC":
                return InformationFootBallClub.LIVERPOOL;
            case "West Bromwich Albion FC":
                return InformationFootBallClub.WEST_BROM_WICH;
            case "Manchester City FC":
                return InformationFootBallClub.MAN_CITY;
            case "Aston Villa FC":
                return InformationFootBallClub.ASTER_VILA;

            //Laliga
            case "Real Madrid CF":
                return InformationFootBallClub.REAL_MADRID_CLUB_DE_FÚTBOL;
            case "RC Celta de Vigo":
                return InformationFootBallClub.REAL_CLUB_CELTA_DE_VIGO;
            case "FC Barcelona":
                return InformationFootBallClub.FC_BARCELONA;
            case "Club Atlético de Madrid":
                return InformationFootBallClub.CLUB_ATLÉTICO_DE_MADRID;
            case "Villarreal CF":
                return InformationFootBallClub.VILLARREAL_CLUB_DE_FÚTBOL;
            case "RC Deportivo La Coruna":
                return InformationFootBallClub.REAL_CLUB_DEPORTIVO_DE_LA_CORUÑA;
            case "SD Eibar":
                return InformationFootBallClub.SD_EIBAR;
            case "Valencia CF":
                return InformationFootBallClub.VALENCIA_CLUB_DE_FÚTBOL;
            case "RCD Espanyol":
                return InformationFootBallClub.REIAL_CLUB_DEPORTIU_ESPANYOL;
            case "Real Betis":
                return InformationFootBallClub.REAL_BETIS_BALOMPIÉ;
            case "Getafe CF":
                return InformationFootBallClub.GETAFE_CLUB_DE_FÚTBOL;
            case "Sporting Gijón":
                return InformationFootBallClub.REAL_SPORTING_DE_GIJÓN;
            case "Sevilla FC":
                return InformationFootBallClub.SEVILLA_FC;
            case "Athletic Club":
                return InformationFootBallClub.ATHLETIC_CLUB_BILBAO;
            case "Rayo Vallecano de Madrid":
                return InformationFootBallClub.RAYO_VALLECANO;
            case "Real Sociedad de Fútbol":
                return InformationFootBallClub.REAL_SOCIEDAD_DE_FÚTBOL;
            case "Málaga CF":
                return InformationFootBallClub.MÁLAGA_CLUB_DE_FÚTBOL;
            case "Levante UD":
                return InformationFootBallClub.LEVANTE_UD;
            case "UD Las Palmas":
                return InformationFootBallClub.UD_LAS_PALMAS;
            case "Granada CF":
                return InformationFootBallClub.GRANADA_CF;

            //Bundesliga
            case "FC Bayern München":
                return InformationFootBallClub.BAYEN_MUNCHEN;
            case "Borussia Dortmund":
                return InformationFootBallClub.BV_BORUSSIA_DORTMUND;
            case "FC Schalke 04":
                return InformationFootBallClub.FC_SCHALKE_04;
            case "VfL Wolfsburg":
                return InformationFootBallClub.VFL_WOLFSBURG;
            case "Hertha BSC":
                return InformationFootBallClub.HERTHA_BSC;
            case "1. FC Köln":
                return InformationFootBallClub.FCKOLN;
            case "Bayer Leverkusen":
                return InformationFootBallClub.BAYER_LEVERKUNSENT;
            case "FC Ingolstadt 04":
                return InformationFootBallClub.FC_INGOLSTADT_04;
            case "Hamburger SV":
                return InformationFootBallClub.HAMBURGER_SV;
            case "SV Darmstadt 98":
                return InformationFootBallClub.SV_DARMSTADT_1898;
            case "Bor. Mönchengladbach":
                return InformationFootBallClub.BORUSIA_MONGLAD;
            case "1. FSV Mainz 05":
                return InformationFootBallClub.FSV_MAINZ_05;
            case "Eintracht Frankfurt":
                return InformationFootBallClub.EINTRACHT_FRANKFURT;
            case "Hannover 96":
                return InformationFootBallClub.HANNOVER_96;
            case "VfB Stuttgart":
                return InformationFootBallClub.STUTTGRAT;
            case "Werder Bremen":
                return InformationFootBallClub.SV_WERDER_BREMEN;
            case "TSG 1899 Hoffenheim":
                return InformationFootBallClub.TSG_1899_HOFFENHEIM;
            case "FC Augsburg":
                return InformationFootBallClub.FC_AUGSBURG;

            //Seria
            case "ACF Fiorentina":
                return InformationFootBallClub.ACF_FIORENTINA;
            case "AS Roma":
                return InformationFootBallClub.AS_ROMA;
            case "FC Internazionale Milano":
                return InformationFootBallClub.FC_INTERNAZIONALE_MILANO;
            case "SSC Napoli":
                return InformationFootBallClub.SSC_NAPOLI;
            case "US Sassuolo Calcio":
                return InformationFootBallClub.US_SASSUOLO_CALCIO;
            case "SS Lazio":
                return InformationFootBallClub.SS_LAZIO;
            case "Torino FC":
                return InformationFootBallClub.TORINO_FC;
            case "Atalanta BC":
                return InformationFootBallClub.ATALANTA_BERGAMASCA_CALCIO;
            case "AC Chievo Verona":
                return InformationFootBallClub.AC_CHIEVO_VERONA;
            case "UC Sampdoria":
                return InformationFootBallClub.UC_SAMPDORIA;
            case "US Cittá di Palermo":
                return InformationFootBallClub.US_CITTÀ_DI_PALERMO;
            case "Genoa CFC":
                return InformationFootBallClub.GENOA_CFC;
            case "AC Milan":
                return InformationFootBallClub.AC_MILAN;
            case "Juventus Turin":
                return InformationFootBallClub.JUVENTUS_FC;
            case "Udinese Calcio":
                return InformationFootBallClub.UDINESE_CALCIO;
            case "Frosinone Calcio":
                return InformationFootBallClub.FROSINONE_CALCIO;
            case "Empoli FC":
                return InformationFootBallClub.EMPOLI_FC;
            case "Hellas Verona FC":
                return InformationFootBallClub.HELLAS_VERONA_FC;
            case "Carpi FC":
                return InformationFootBallClub.CARPI_FC_1909;
            case "Bologna FC":
                return InformationFootBallClub.BOLOGNA_FC_1909;

            //Ligue 1
            case "Paris Saint-Germain":
                return InformationFootBallClub.PARIS_SAINT_GERMAIN_FC;
            case "Angers SCO":
                return InformationFootBallClub.ANGERS_SCO;
            case "SM Caen":
                return InformationFootBallClub.STADE_MALHERBE_CAEN;
            case "AS Saint-Étienne":
                return InformationFootBallClub.AS_SAINT_ÉTIENNE;
            case "OGC Nice":
                return InformationFootBallClub.O_G_C_NICE_CÔTE_D_AZUR;
            case "Olympique Lyonnais":
                return InformationFootBallClub.OLYMPIQUE_LYONNAIS;
            case "Stade Rennais FC":
                return InformationFootBallClub.STADE_RENNAIS_FC;
            case "Stade de Reims":
                return InformationFootBallClub.STADE_DE_REIMS;
            case "FC Lorient":
                return InformationFootBallClub.FC_LORIENT;
            case "AS Monaco FC":
                return InformationFootBallClub.ASP_MONACO_FC;
            case "EA Guingamp":
                return InformationFootBallClub.EN_AVANT_GUINGAMP;
            case "FC Nantes":
                return InformationFootBallClub.FC_NANTES;
            case "OSC Lille":
                return InformationFootBallClub.LILLE_OSC_MÉTROPOLE;
            case "FC Girondins de Bordeaux":
                return InformationFootBallClub.FC_GIRONDINS_DE_BORDEAUX;
            case "SC Bastia":
                return InformationFootBallClub.SC_BASTIA;
            case "Olympique de Marseille":
                return InformationFootBallClub.OLYMPIQUE_DE_MARSEILLE;
            case "Toulouse FC":
                return InformationFootBallClub.TOULOUSE_FC;
            case "Montpellier Hérault SC":
                return InformationFootBallClub.MONTPELLIER_HSC;
            case "ES Troyes AC":
                return InformationFootBallClub.ESPÉRANCE_SPORTIVE_TROYES_AUBE_CHAMPAGNE;
            case "Gazélec Ajaccio":
                return InformationFootBallClub.GAZÉLEC_FCO_AJACCIO;
            //c1-----------------
            case "PSV Eindhoven":
                break;
            case "CSKA Moscow":
                break;
            case "Shakhtar Donetsk":
                break;
            case "Malmö FF":
                break;
            case "Galatasaray SK":
                break;
            case "Benfica Lissabon":
                break;
            case "FC Astana":
                break;
            case "Olympiacos F.C.":
                break;
            case "KAA Gent":
                break;
            case "FC Zenit St. Petersburg":
                break;
            case "FK BATE Baryssau":
                break;
            case "Maccabi Tel Aviv":
                break;
            case "Dynamo Kyiv":
                break;
            case "FC Porto":
                break;
            case "GNK Dinamo Zagreb":
                break;
        }
        return InformationFootBallClub.CHELSEA;
    }

    public static String getUrlLtdFc(Context mContext, String name) {

        switch (name) {
            //Primer League
            case "Manchester United FC":
                return InformationFootBallClub.MANCHESTER_UNITED_FC_LTD;
            case "AFC Bournemouth":
                return InformationFootBallClub.AFC_BOURNEMOUTH_LTD;
            case "Tottenham Hotspur FC":
                return InformationFootBallClub.TOTTENHAM_HOTSPUR_FC_LTD;
            case "Everton FC":
                return InformationFootBallClub.EVERTON_FC_LTD;
            case "Watford FC":
                return InformationFootBallClub.WATFORD_FC_LTD;
            case "Leicester City FC":
                return InformationFootBallClub.LEICESTER_CITY_FC_LTD;
            case "Sunderland AFC":
                return InformationFootBallClub.SUNDERLAND_AFC_LTD;
            case "Norwich City FC":
                return InformationFootBallClub.NORWICH_CITY_FC_LTD;
            case "Crystal Palace FC":
                return InformationFootBallClub.CRYSTAL_PALACE_FC_LTD;
            case "Chelsea FC":
                return InformationFootBallClub.CHELSEA_FC_LTD;
            case "Swansea City FC":
                return InformationFootBallClub.SWANSEA_CITY_AFC;
            case "Newcastle United FC":
                return InformationFootBallClub.NEWCASTLE_UNITED_FC_LTD;
            case "Southampton FC":
                return InformationFootBallClub.SOUTHAMPTON_FC_LTD;
            case "Arsenal FC":
                return InformationFootBallClub.ARSENAL_FC_LTD;
            case "West Ham United FC":
                return InformationFootBallClub.WEST_HAM_UNITED_FC_LTD;
            case "Stoke City FC":
                return InformationFootBallClub.STOKE_CITY_FC_LTD;
            case "Liverpool FC":
                return InformationFootBallClub.LIVERPOOL_FC_LTD;
            case "West Bromwich Albion FC":
                return InformationFootBallClub.WEST_BROM_WICH_LTD;
            case "Manchester City FC":
                return InformationFootBallClub.MANCHESTER_CITY_FC_LTD;
            case "Aston Villa FC":
                return InformationFootBallClub.ASTON_VILLA_FC_LTD;

            //Laliga
            case "Real Madrid CF":
                return InformationFootBallClub.REAL_MADRID_CLUB_DE_FÚTBOL_LTD;
            case "RC Celta de Vigo":
                return InformationFootBallClub.REAL_CLUB_CELTA_DE_VIGO_LTD;
            case "FC Barcelona":
                return InformationFootBallClub.FC_BARCELONA_LTD;
            case "Club Atlético de Madrid":
                return InformationFootBallClub.CLUB_ATLÉTICO_DE_MADRID_LTD;
            case "Villarreal CF":
                return InformationFootBallClub.VILLARREAL_CLUB_DE_FÚTBOL_LTD;
            case "RC Deportivo La Coruna":
                return InformationFootBallClub.REAL_CLUB_DEPORTIVO_DE_LA_CORUÑA_LTD;
            case "SD Eibar":
                return InformationFootBallClub.SD_EIBAR_LTD;
            case "Valencia CF":
                return InformationFootBallClub.VALENCIA_CLUB_DE_FÚTBOL_LTD;
            case "RCD Espanyol":
                return InformationFootBallClub.REIAL_CLUB_DEPORTIU_ESPANYOL_LTD;
            case "Real Betis":
                return InformationFootBallClub.REAL_BETIS_BALOMPIÉ_LTD;
            case "Getafe CF":
                return InformationFootBallClub.GETAFE_CLUB_DE_FÚTBOL_LTD;
            case "Sporting Gijón":
                return InformationFootBallClub.REAL_SPORTING_DE_GIJÓN_LTD;
            case "Sevilla FC":
                return InformationFootBallClub.SEVILLA_FC_LTD;
            case "Athletic Club":
                return InformationFootBallClub.ATHLETIC_CLUB_BILBAO_LTD;
            case "Rayo Vallecano de Madrid":
                return InformationFootBallClub.RAYO_VALLECANO_LTD;
            case "Real Sociedad de Fútbol":
                return InformationFootBallClub.REAL_SOCIEDAD_DE_FÚTBOL_LTD;
            case "Málaga CF":
                return InformationFootBallClub.MÁLAGA_CLUB_DE_FÚTBOL_LTD;
            case "Levante UD":
                return InformationFootBallClub.LEVANTE_UD_LTD;
            case "UD Las Palmas":
                return InformationFootBallClub.UD_LAS_PALMAS_LTD;
            case "Granada CF":
                return InformationFootBallClub.GRANADA_CF_LTD;

            //Bundesliga
            case "FC Bayern München":
                return InformationFootBallClub.FC_BAYERN_MÜNCHEN_LTD;
            case "Borussia Dortmund":
                return InformationFootBallClub.BV_BORUSSIA_09_DORTMUND_LTD;
            case "FC Schalke 04":
                return InformationFootBallClub.FC_SCHALKE_04_LTD;
            case "VfL Wolfsburg":
                return InformationFootBallClub.VFL_WOLFSBURG_LTD;
            case "Hertha BSC":
                return InformationFootBallClub.HERTHA_BSC_LTD;
            case "1. FC Köln":
                return InformationFootBallClub.FC_KOLN_LTD;
            case "Bayer Leverkusen":
                return InformationFootBallClub.TSV_BAYER_04_LEVERKUSEN_LTD;
            case "FC Ingolstadt 04":
                return InformationFootBallClub.FC_INGOLSTADT_04_LTD;
            case "Hamburger SV":
                return InformationFootBallClub.HAMBURGER_SV_LTD;
            case "SV Darmstadt 98":
                return InformationFootBallClub.SV_DARMSTADT_1898_LTD;
            case "Bor. Mönchengladbach":
                return InformationFootBallClub.BORUSSIA_VFL_MÖNCHENGLADBACH_LTD;
            case "1. FSV Mainz 05":
                return InformationFootBallClub.FSV_MAINZ_05_LTD;
            case "Eintracht Frankfurt":
                return InformationFootBallClub.EINTRACHT_FRANKFURT_LTD;
            case "Hannover 96":
                return InformationFootBallClub.HANNOVER_96_LTD;
            case "VfB Stuttgart":
                return InformationFootBallClub.VFB_STUTTGART_1893_LTD;
            case "Werder Bremen":
                return InformationFootBallClub.SV_WERDER_BREMEN_LTD;
            case "TSG 1899 Hoffenheim":
                return InformationFootBallClub.TSG_1899_HOFFENHEIM_LTD;
            case "FC Augsburg":
                return InformationFootBallClub.FC_AUGSBURG_LTD;

            //Seria
            case "ACF Fiorentina":
                return InformationFootBallClub.ACF_FIORENTINA_LTD;
            case "AS Roma":
                return InformationFootBallClub.AS_ROMA_LTD;
            case "FC Internazionale Milano":
                return InformationFootBallClub.FC_INTERNAZIONALE_MILANO_LTD;
            case "SSC Napoli":
                return InformationFootBallClub.SSC_NAPOLI_LTD;
            case "US Sassuolo Calcio":
                return InformationFootBallClub.US_SASSUOLO_CALCIO_LTD;
            case "SS Lazio":
                return InformationFootBallClub.SS_LAZIO_LTD;
            case "Torino FC":
                return InformationFootBallClub.TORINO_FC_LTD;
            case "Atalanta BC":
                return InformationFootBallClub.ATALANTA_BERGAMASCA_CALCIO_LTD;
            case "AC Chievo Verona":
                return InformationFootBallClub.AC_CHIEVO_VERONA_LTD;
            case "UC Sampdoria":
                return InformationFootBallClub.UC_SAMPDORIA_LTD;
            case "US Cittá di Palermo":
                return InformationFootBallClub.US_CITTÀ_DI_PALERMO_LTD;
            case "Genoa CFC":
                return InformationFootBallClub.GENOA_CFC_LTD;
            case "AC Milan":
                return InformationFootBallClub.AC_MILAN_LTD;
            case "Juventus Turin":
                return InformationFootBallClub.JUVENTUS_FC_LTD;
            case "Udinese Calcio":
                return InformationFootBallClub.UDINESE_CALCIO_LTD;
            case "Frosinone Calcio":
                return InformationFootBallClub.FROSINONE_CALCIO_LTD;
            case "Empoli FC":
                return InformationFootBallClub.EMPOLI_FC_LTD;
            case "Hellas Verona FC":
                return InformationFootBallClub.HELLAS_VERONA_FC_LTD;
            case "Carpi FC":
                return InformationFootBallClub.CARPI_FC_1909_LTD;
            case "Bologna FC":
                return InformationFootBallClub.BOLOGNA_FC_1909_LTD;

            //Ligue 1
            case "Paris Saint-Germain":
                return InformationFootBallClub.PARIS_SAINT_GERMAIN_FC_LTD;
            case "Angers SCO":
                return InformationFootBallClub.ANGERS_SCO_LTD;
            case "SM Caen":
                return InformationFootBallClub.STADE_MALHERBE_CAEN_LTD;
            case "AS Saint-Étienne":
                return InformationFootBallClub.AS_SAINT_ÉTIENNE_LTD;
            case "OGC Nice":
                return InformationFootBallClub.O_G_C_NICE_CÔTE_D_AZUR_LTD;
            case "Olympique Lyonnais":
                return InformationFootBallClub.OLYMPIQUE_LYONNAIS_LTD;
            case "Stade Rennais FC":
                return InformationFootBallClub.STADE_RENNAIS_FC_LTD;
            case "Stade de Reims":
                return InformationFootBallClub.STADE_DE_REIMS_LTD;
            case "FC Lorient":
                return InformationFootBallClub.FC_LORIENT_LTD;
            case "AS Monaco FC":
                return InformationFootBallClub.ASP_MONACO_FC_LTD;
            case "EA Guingamp":
                return InformationFootBallClub.EN_AVANT_GUINGAMP_LTD;
            case "FC Nantes":
                return InformationFootBallClub.FC_NANTES_LTD;
            case "OSC Lille":
                return InformationFootBallClub.LILLE_OSC_MÉTROPOLE_LTD;
            case "FC Girondins de Bordeaux":
                return InformationFootBallClub.FC_GIRONDINS_DE_BORDEAUX_LTD;
            case "SC Bastia":
                return InformationFootBallClub.SC_BASTIA_LTD;
            case "Olympique de Marseille":
                return InformationFootBallClub.OLYMPIQUE_DE_MARSEILLE_LTD;
            case "Toulouse FC":
                return InformationFootBallClub.TOULOUSE_FC_LTD;
            case "Montpellier Hérault SC":
                return InformationFootBallClub.MONTPELLIER_HSC_LTD;
            case "ES Troyes AC":
                return InformationFootBallClub.ESPÉRANCE_SPORTIVE_TROYES_AUBE_CHAMPAGNE_LTD;
            case "Gazélec Ajaccio":
                return InformationFootBallClub.GAZÉLEC_FCO_AJACCIO_LTD;
            //c1-----------------
            case "PSV Eindhoven":
                break;
            case "CSKA Moscow":
                break;
            case "Shakhtar Donetsk":
                break;
            case "Malmö FF":
                break;
            case "Galatasaray SK":
                break;
            case "Benfica Lissabon":
                break;
            case "FC Astana":
                break;
            case "Olympiacos F.C.":
                break;
            case "KAA Gent":
                break;
            case "FC Zenit St. Petersburg":
                break;
            case "FK BATE Baryssau":
                break;
            case "Maccabi Tel Aviv":
                break;
            case "Dynamo Kyiv":
                break;
            case "FC Porto":
                break;
            case "GNK Dinamo Zagreb":
                break;
        }
        return InformationFootBallClub.CHELSEA_FC_LTD;
    }

}