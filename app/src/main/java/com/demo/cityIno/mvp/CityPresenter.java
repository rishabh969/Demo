package com.demo.cityIno.mvp;

import com.demo.cityIno.model.City;
import com.demo.cityIno.model.CityRepo;
import com.demo.cityIno.model.ResponseModel;
import com.demo.cityIno.util.EspressoTestingIdlingResource;

import java.util.List;

/**
 * Created by Rishabh.
 */

public final class CityPresenter implements CityListContract.Presenter  {

    private CityListContract.View view;

    private CityRepo mclient;


    public CityPresenter(CityListContract.View view, CityRepo client) {
        this.view = view;
        mclient = client;
    }


    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void loadMoviewList() {
        view.showProgress();
        mclient.getMovieList(callback);
        // required for espresso UI testing
        EspressoTestingIdlingResource.increment();
    }

    // callback mechanism , onResponse will be triggered with response
    // by simulatemovieclient(or your network or database process) and pass the response to view
    private final CityListContract.OnResponseCallback callback = new CityListContract.OnResponseCallback() {
        @Override
        public void onResponse(List<ResponseModel.RowsBean> cities) {
            view.showMovieList(cities);
            view.hideProgress();
            EspressoTestingIdlingResource.decrement();
        }

        @Override
        public void onError(String errMsg) {
            view.hideProgress();
            view.showLoadingError(errMsg);
            EspressoTestingIdlingResource.decrement();
        }
    };
}
