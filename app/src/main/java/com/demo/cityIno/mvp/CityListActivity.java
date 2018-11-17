package com.demo.cityIno.mvp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.cityIno.R;
import com.demo.cityIno.model.City;
import com.demo.cityIno.model.ResponseModel;
import com.demo.cityIno.model.SimulateCityClient;

import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.*;

/**
 * Created by Rishabh.
 */

public class CityListActivity extends AppCompatActivity implements CityListContract.View,CityListContract.ActionTitle{

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private SwipeRefreshLayout swipeLayout;
    private CityListContract.Presenter presenter;
    private TextView tv_empty_msg;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        initViews();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Title");
    }

    private void initViews(){
        presenter = new CityPresenter(CityListActivity.this, new SimulateCityClient(this));
        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_list); // list
        tv_empty_msg = (TextView)findViewById(R.id.swipe_msg_tv); // empty message
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // for vertical liner list
        cityAdapter = new CityAdapter(this);
        recyclerView.setAdapter(cityAdapter);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(listener);
        swipeLayout.setColorSchemeColors( // colors for progress dialog
                ContextCompat.getColor(CityListActivity.this, R.color.colorPrimary),
                ContextCompat.getColor(CityListActivity.this, R.color.colorAccent),
                ContextCompat.getColor(CityListActivity.this, android.R.color.holo_green_light)
        );


        presenter.loadMoviewList();
    }


    private final OnRefreshListener listener = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            presenter.loadMoviewList();
        }
    };

    // for future, to show progress
    @Override
    public void showProgress() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeLayout.setRefreshing(false);
    }

    // toggle the visibility of empty textview or list
    // display list only when response it not empty
    private void showORHideListView(boolean flag){
        if (flag){
            tv_empty_msg.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            tv_empty_msg.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMovieList(List<ResponseModel.RowsBean> cities) {
        if (!cities.isEmpty()){
            cityAdapter.setList(cities);
            showORHideListView(true);
        }
    }

    @Override
    public void showLoadingError(String errMsg) {
        hideProgressAndShowErr(errMsg);
        showORHideListView(false);
    }

    private void hideProgressAndShowErr(String msg){
        tv_empty_msg.setVisibility(View.VISIBLE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        showORHideListView(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    public void sendTitle(String title) {
        actionBar.setTitle(""+title);
    }
}