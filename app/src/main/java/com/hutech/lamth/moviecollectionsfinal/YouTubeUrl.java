package com.hutech.lamth.moviecollectionsfinal;

import android.content.Intent;

public class YouTubeUrl {
    private static String URL;

    public YouTubeUrl(){}
    public YouTubeUrl(String URL){
        this.URL = URL;
    }
    public static String getUrl(){
        return URL;
    }
    public void setUrl(String URL)
    {
        this.URL = URL;
    }
}
