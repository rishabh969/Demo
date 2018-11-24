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
import com.demo.cityIno.model.ResponseModel;
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

        if(listItem.get(position).getTitle().equalsIgnoreCase(context.getResources().getString(R.string.checknul))) {
            holder.txtTitle.setText("");
        }else{
            holder.txtTitle.setText(""+listItem.get(position).getTitle());
        }
        if(listItem.get(position).getDescription().equalsIgnoreCase(context.getResources().getString(R.string.checknul))) {
            holder.txtDescription.setText("");
        }else {
            holder.txtDescription.setText(""+listItem.get(position).getDescription());
        }

        if(listItem.get(position).getImageHref()!=null) {
            Glide.with(context)
                    .load(Uri.parse(listItem.get(position).getImageHref())).placeholder(R.drawable.star)
                    .into(holder.imgCity);
        }

        holder.rowView.setOnClickListener(new View.OnClickListener() {
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
        Log.e(TAG, context.getResources().getString(R.string.next)+listItem.size() );
    }


    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgCity;
        LinearLayout rowView;

        public MovieHolder(View v) {
            super(v);
            rowView = (LinearLayout) v.findViewById(R.id.ll_card);
            txtTitle = (TextView) v.findViewById(R.id.txt_title);
            txtDescription = (TextView) v.findViewById(R.id.txt_description);
            imgCity = (ImageView) v.findViewById(R.id.img_city);
        }
    }
}
