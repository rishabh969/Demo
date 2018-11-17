package com.demo.cityIno.mvp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.demo.cityIno.R;
import com.demo.cityIno.model.City;
import com.demo.cityIno.model.ResponseModel;
import com.demo.cityIno.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rishabh
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MovieHolder> {

    private final Context context;
    private final List<ResponseModel.RowsBean> listItem = new ArrayList<>();
    private static final String TAG = "CityAdapter";

    public CityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_model,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        Log.e(TAG, "onBindViewHolder: "+position);
        if(listItem.get(position).getTitle().equalsIgnoreCase("null")) {
            holder.title.setText("");
        }else{
            holder.title.setText(""+listItem.get(position).getTitle());
        }
        if(listItem.get(position).getDescription().equalsIgnoreCase("null")) {
            holder.description.setText("");
        }else {
            holder.description.setText(""+listItem.get(position).getDescription());
        }

        if(listItem.get(position).getImageHref()!=null) {
            Glide.with(context)
                    .load(Uri.parse(listItem.get(position).getImageHref())).placeholder(R.drawable.star)
                    .into(holder.image);
        }

        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listItem.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setList(List<ResponseModel.RowsBean> list){
        listItem.clear();
        listItem.addAll(list);
        notifyDataSetChanged();
        Log.e(TAG, "onNext: "+listItem.size() );
    }


    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView view;
        ImageView image;
        LinearLayout moviesLayout;

        public MovieHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.des);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
}
