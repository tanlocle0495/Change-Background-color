package com.loc.demochangecoloronbackgroud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;

public class Main2Activity extends AppCompatActivity {
    LineColorPicker colorPicker;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Pref pref;

    FrameLayout statusBar;
    Button button;
    Toolbar toolbar;
    String ColorSelected;

    public String getColorSelected() {
        return ColorSelected;
    }

    public void setColorSelected(String colorSelected) {
        ColorSelected = colorSelected;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new Pref(this);

        pref.settingTheme(pref.getShare());
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button = (Button) findViewById(R.id.button);
        button.setEnabled(false);
        setSupportActionBar(toolbar);
        colorPicker = (LineColorPicker) findViewById(R.id.picker);
        statusBar = (FrameLayout) findViewById(R.id.statusBar);
        navigationBarStatusBar();
        colorPicker.setColors(new int[]{0xFF009688, 0xFFFFC107, 0xFF3F51B5,0xFF4CAF50});
        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                Log.d("SASAS", Integer.toHexString(c));
                setColorSelected(Integer.toHexString(c));
                button.setEnabled(true);
            }
        });
//        int color = colorPicker.getColor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref.dataShare(getColorSelected());
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                finish();
            }
        });
    }


    public void navigationBarStatusBar() {

        // Fix portrait issues
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Fix issues for KitKat setting Status Bar color primary
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
                getWindow().setStatusBarColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                getWindow().setStatusBarColor(color);
            }
        }
    }

}
