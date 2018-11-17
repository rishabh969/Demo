package com.demo.cityIno.mvp;


import com.demo.cityIno.model.City;
import com.demo.cityIno.model.ResponseModel;

import java.util.List;


/**
 * Created by Rishabh.
 */

/**
 * This specifies the contract between the view and the presenter.
 */
public interface CityListContract {

    interface View {
        void showProgress();
        void hideProgress();
        void showMovieList(List<ResponseModel.RowsBean> cities);
        void showLoadingError(String errMsg);

    }

    interface ActionTitle{
        void sendTitle(String title);
    }

    interface Presenter{
        void loadMoviewList();
        void dropView();
    }

    interface OnResponseCallback{
        void onResponse(List<ResponseModel.RowsBean> cities);
        void onError(String errMsg);
    }
}
