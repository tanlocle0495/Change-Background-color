package com.loc.demochangecoloronbackgroud;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.util.TypedValue;
import android.widget.FrameLayout;

/**
 * Created by loc on 28/11/2015.
 */
public class Pref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor shEditor;
    Activity activity;

    public Pref(Context context) {
        sharedPreferences = context.getSharedPreferences("colorVal", 0);
        shEditor = sharedPreferences.edit();
        activity = ((Activity) context);
    }

    public void dataShare(String id) {
        shEditor.putString("Color", id);
        shEditor.commit();
    }


    public String getShare() {
        return sharedPreferences.getString("Color", "");

    }

    public void settingTheme(String theme) {

        switch (theme) {
            case "ff009688":
                activity.setTheme(R.style.AppTheme);
//                setStatusBarColor(findViewById(R.id.statusBar),"#FFF");
                break;
            case "ffffc107":
                activity.setTheme(R.style.AppTheme2);
                break;
            case "ff3f51b5":
                activity.setTheme(R.style.AppTheme3);
                break;
            case "ff4caf50":
                activity.setTheme(R.style.AppTheme4);
                break;
//
        }
    }


}
