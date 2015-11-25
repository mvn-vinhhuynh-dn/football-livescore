package com.androidbelieve.footballlivescore.util;

import android.content.Context;
import android.widget.ImageView;

import com.androidbelieve.footballlivescore.R;

/**
 * Created by phulx on 04/11/2015.
 */
public class CheckLogoC1 {

    public static void setLogo(Context mContext, ImageView imageView, String name) {
        switch (name) {
            //Primer League
            case "Real Madrid":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_real_madrid));
                break;
            case "PSG":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_psg));
                break;
            case "Shakhtar Donetsk":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_shakhtar_donetsk));
                break;
            case "Malmö FF":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_malmo));
                break;
            case "Manchester United":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_manchester_united));
                break;
            case "PSV":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_psv_eindhoven));
                break;
            case "Wolfsburg":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_wolfsburg));
                break;
            case "CSKA Moskva":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_cska_moscow));
                break;
            case "Benfica":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_benfica));
                break;
            case "Atlético Madrid":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_atletico_madrid));
                break;
            case "Galatasaray":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_galatasaray));
                break;
            case "Astana":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_astana));
                break;
            case "Manchester City":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_manchester_city));
                break;
            case "Juventus":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_juventus));
                break;
            case "Sevilla":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_sevilla));
                break;
            case "Borussia M'gladbach":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_monchengladbach));
                break;
            case "Barcelona":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_barcelona));
                break;
            case "Bayer Leverkusen":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_leverkusen));
                break;
            case "BATE":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bate_baryssau));
                break;
            case "Roma":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_as_roma));
                break;
            case "Bayern München":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_bayern_munich));
                break;
            case "Olympiakos Piraeus":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_olympiakos));
                break;
            case "Arsenal":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_arsenal));
                break;
            case "Dinamo Zagreb":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dinamo_zagreb));
                break;
            case "Porto":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_porto));
                break;
            case "Dynamo Kyiv":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_kyiv));
                break;
            case "Chelsea":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_chelsea));
                break;
            case "Maccabi Tel Aviv":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_maccabi_tel));
                break;
            case "Zenit":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_zenit_saint_petersburg));
                break;
            case "Valencia":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_valencia));
                break;
            case "Gent":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_kaa_gent));
                break;
            case "Olympique Lyonnais":
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_lyon));
                break;
        }
    }
}