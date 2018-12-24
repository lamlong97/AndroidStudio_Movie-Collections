package com.hutech.lamth.moviecollectionsfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;
import com.hutech.lamth.moviecollectionsfinal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListMovieAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Movie> movieList;

    public ListMovieAdapter(Context context, int layout, List<Movie> movieList) {
        this.context = context;
        this.layout = layout;
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgPoster;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.imgPoster = convertView.findViewById(R.id.imgListHorizontalPoster);
            holder.imgPoster.setScaleType(ImageView.ScaleType.FIT_XY);
            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Movie movie = movieList.get(position);

        //holder.imgPoster.setImageResource(Integer.parseInt(movies.getPoster()));

        Picasso.get().load(movie.getPoster()).into(holder.imgPoster);
        holder.txtTitle.setText(movie.getTitle());

        return convertView;
    }
}
