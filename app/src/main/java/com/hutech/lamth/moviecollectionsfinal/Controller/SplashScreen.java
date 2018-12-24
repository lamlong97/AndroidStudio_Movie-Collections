package com.hutech.lamth.moviecollectionsfinal.Controller;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hutech.lamth.moviecollectionsfinal.MainActivity;
import com.hutech.lamth.moviecollectionsfinal.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(500)
                .withBackgroundResource(android.R.color.holo_red_light)
                .withLogo(R.drawable.ic_movie_filter_black_24dp)
                .withFooterText("Copyright 2018")
                .withAfterLogoText("Movie collection");

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "pacifico.ttf");
        //After Logo
        easySplashScreen.getAfterLogoTextView().setTypeface(pacificoFont);
        easySplashScreen.getAfterLogoTextView().setTextColor(Color.WHITE);
        easySplashScreen.getAfterLogoTextView().setTextSize(35);
        easySplashScreen.getAfterLogoTextView().setPadding(10,50,10,10);

        //Logo
        easySplashScreen.getLogo().setMaxHeight(250);
        easySplashScreen.getLogo().setMaxWidth(250);

        //Footer
        easySplashScreen.getFooterTextView().setTextColor(Color.WHITE);
        easySplashScreen.getFooterTextView().setPadding(10,10,10,50);

        View view = easySplashScreen.create();
        setContentView(view);
    }
}
