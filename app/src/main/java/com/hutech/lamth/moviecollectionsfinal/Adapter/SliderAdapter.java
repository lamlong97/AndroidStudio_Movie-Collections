package com.hutech.lamth.moviecollectionsfinal.Adapter;

import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;
import com.hutech.lamth.moviecollectionsfinal.Picasso.PicassoImageLoadingService;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class SliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter {
    @Override
    public int getItemCount() {
        return 3;
    }

    Movie movie = new Movie();
    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("http://cdn.collider.com/wp-content/uploads/inception_movie_poster_banner_04.jpg");
                break;
            case 1:
                viewHolder.bindImageSlide("http://thelevel.my/wp-content/uploads/2018/04/30688984_2044170755848521_2701995743031328768_n.png");
                break;
            case 2:
                viewHolder.bindImageSlide("https://img00.deviantart.net/1c8e/i/2017/231/1/e/justice_league_movie_banner_by_arkhamnatic-dbhzwh4.png");
                break;
        }
    }
}
