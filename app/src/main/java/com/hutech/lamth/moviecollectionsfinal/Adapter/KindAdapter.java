package com.hutech.lamth.moviecollectionsfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutech.lamth.moviecollectionsfinal.Objects.Kind;
import com.hutech.lamth.moviecollectionsfinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class KindAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Kind> kindList;

    public KindAdapter(Context context, int layout, ArrayList<Kind> kindList) {
        this.context = context;
        this.layout = layout;
        this.kindList = kindList;
    }

    @Override
    public int getCount() {
        return kindList.size();
    }

    @Override
    public Object getItem(int position) {
        return kindList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtTheLoai,txtDescription;
        ImageView imgTheLoaiIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTheLoai = convertView.findViewById(R.id.txtTheLoai);
            holder.txtDescription = convertView.findViewById(R.id.txtDescription);
            holder.imgTheLoaiIcon = convertView.findViewById(R.id.imgTheLoaiIcon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Kind kind = kindList.get(position);

        holder.txtTheLoai.setText("Phim " + kind.getName().toLowerCase());
        holder.txtDescription.setText(kind.getDescription());
        Picasso.get().load(kind.getIcon()).into(holder.imgTheLoaiIcon);

        return convertView;
    }
}
