package com.example.module;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;


public class Prefs extends PreferenceFragmentCompat {

    private static final String soundtrack = "soundtrack";
    private static final String rapidMi = "rapidMi";
    private static final String rapidDep = "rapidDep";
    private static final String dirP = "dirP";
    private static final String speedP = "speedP";
    private static final String speedS = "speedS";



    @Override
    public void onCreatePreferences(Bundle b, String s) {
        Context ct = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(ct);

        var music = new SwitchPreference(ct);
        music.setTitle(R.string.bgm);
        music.setDefaultValue(true);
        music.setSummaryOff(R.string.bgmon);
        music.setSummaryOn(R.string.bgmoff);
        music.setKey(soundtrack);

        var rapmi = new SwitchPreference(ct);
        rapmi.setTitle(R.string.mis);
        rapmi.setDefaultValue(true);
        rapmi.setSummaryOff(R.string.yes);
        rapmi.setSummaryOn(R.string.no);
        rapmi.setKey(rapidMi);

        var rapd = new SwitchPreference(ct);
        rapd.setTitle(R.string.dep);
        rapd.setDefaultValue(true);
        rapmi.setSummaryOff(R.string.yes);
        rapmi.setSummaryOn(R.string.no);
        rapd.setKey(rapidDep);

        var dP = new ListPreference(ct);
        dP.setTitle(R.string.dirh);
        dP.setSummary(R.string.bothside);
        dP.setKey(dirP);
        dP.setEntries(R.array.direntries);
        dP.setEntryValues(new String[] {"1", "2", "3"});

        var sP = new ListPreference(ct);
        sP.setTitle(R.string.hspeed);
        sP.setSummary(R.string.normal);
        sP.setKey(speedP);
        sP.setEntries(R.array.speedentries);
        sP.setEntryValues(new String[] {"30", "15", "8"});

        var sS = new ListPreference(ct);
        sS.setTitle(R.string.sspeed);
        sS.setSummary(R.string.normal);
        sS.setKey(speedS);
        sS.setEntries(R.array.speedentries);
        sS.setEntryValues(new String[] {"30", "15", "8"});

        screen.addPreference(music);
        screen.addPreference(rapmi);
        screen.addPreference(rapd);
        screen.addPreference(dP);
        screen.addPreference(sP);
        screen.addPreference(sS);



        setPreferenceScreen(screen);

    }

    public static boolean sound(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(soundtrack, true);
    }

    public static boolean rmi(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(rapidMi, true);
    }


    public static boolean rd(Context c) {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(rapidDep, true);
    }

    public static int hspeed(Context c) {
        String tmp = PreferenceManager.getDefaultSharedPreferences(c).getString(speedP, "5");
        return Integer.parseInt(tmp);
    }

    public static int uspeed(Context c) {
        String tmp = PreferenceManager.getDefaultSharedPreferences(c).getString(speedS, "5");
        return Integer.parseInt(tmp);
    }

    public static int hdir(Context c) {
        String tmp = PreferenceManager.getDefaultSharedPreferences(c).getString(dirP, "3");
        return Integer.parseInt(tmp);
    }




}
