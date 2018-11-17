package com.demo.citylist;


import com.demo.cityIno.model.CityRepo;
import com.demo.cityIno.model.ResponseModel;
import com.demo.cityIno.mvp.CityListContract;
import com.demo.cityIno.mvp.CityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import com.demo.cityIno.model.ResponseModel.RowsBean;

/**
 * Created by Rishabh.
 */
public class CityPresenterTest{

    private static final Random RANDOM = new Random();

    @Mock
    private CityListContract.View view;

    @Mock
    private CityRepo movieRepo;

    private CityPresenter presenter;


    @Captor
    private ArgumentCaptor<CityListContract.OnResponseCallback> argumentCaptor;

    @Before
    public void setUp(){
        // A convenient way to inject mocks by using the @Mock annotation in Mockito.
        //  For mock injections , initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // get the presenter reference and bind with view for testing
        presenter = new CityPresenter(view,movieRepo);


    }

    @Test
    public void loadMoviewList() throws Exception {
        presenter.loadMoviewList();
        verify(movieRepo,times(1)).getMovieList(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(getList());
        verify(view,times(1)).hideProgress();
        ArgumentCaptor<List> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).showMovieList(entityArgumentCaptor.capture());

        assertTrue(entityArgumentCaptor.getValue().size() == 4);
    }

    @Test
    public void OnError() throws Exception {
        presenter.loadMoviewList();
        verify(movieRepo,times(1)).getMovieList(argumentCaptor.capture());
        argumentCaptor.getValue().onError("Error");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(view,times(1)).showLoadingError(argumentCaptor.capture());
        verify(view).showLoadingError(argumentCaptor.getValue());
    }

    private List<RowsBean> getList() {
        ArrayList<RowsBean> movies = new ArrayList<>();
        try {
            movies.add(new RowsBean("City1", "The Dark Knight",""));
            movies.add(new RowsBean("City1", "The Dark Knight",""));
            movies.add(new RowsBean("City1", "The Dark Knight",""));
            movies.add(new RowsBean("City1", "The Dark Knight",""));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return movies;
    }
}