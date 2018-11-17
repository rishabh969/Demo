package com.demo.cityIno.model;


import com.demo.cityIno.mvp.CityListContract;

/**
 * Created by Rishabh.
 */

public interface CityRepo {
    void getMovieList(CityListContract.OnResponseCallback callback);
}
