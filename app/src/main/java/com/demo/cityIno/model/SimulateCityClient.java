package com.demo.cityIno.model;

import android.os.AsyncTask;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.demo.cityIno.BuildConfig;
import com.demo.cityIno.mvp.CityListActivity;
import com.demo.cityIno.mvp.CityListContract;
import com.demo.cityIno.util.Utility;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Rishabh.
 */

/* class to demonstrate network calls responses
* */
public final class SimulateCityClient implements CityRepo {
    CityListContract.ActionTitle actionTitle;

    public SimulateCityClient(CityListActivity cityListActivity) {
        actionTitle=cityListActivity;
    }

    public void getMovieList(final CityListContract.OnResponseCallback callback)  {

        WebService webService=new WebService(new WebService.NetworkApiCallback() {
            private ResponseModel.RowsBean gbResponse;
            private List<ResponseModel.RowsBean> listItem=new ArrayList<ResponseModel.RowsBean>();
            @Override
            public void onSuccess(String successResponse) {
                try {
                    JSONObject jsonObject = new JSONObject(successResponse);
                    if (jsonObject != null){
                        if(jsonObject.has("title")){
                            String acTitle=jsonObject.getString("title");
                            if(acTitle!=null && !acTitle.equalsIgnoreCase("")){
                                actionTitle.sendTitle(acTitle);
                            }
                        }

                        if (jsonObject.has("rows")) {
                            JSONArray listResponse = jsonObject.getJSONArray("rows");
                            if (listResponse.length() > 0) {
                                for (int i = 0; i < listResponse.length(); i++) {
                                    gbResponse = new ResponseModel.RowsBean();
                                    JSONObject object = listResponse.getJSONObject(i);
                                    gbResponse.setTitle(object.getString("title"));
                                    gbResponse.setDescription(object.getString("description"));
                                    gbResponse.setImageHref(object.getString("imageHref"));
                                    listItem.add(gbResponse);
                                }
                                callback.onResponse(listItem);

                            } else {
                                callback.onError("Error");
                            }
                        }
                }
                } catch (Exception e) {

                }
            }

            @Override
            public void onError(String errorResponse) {
               // view.hideProgress();
               // view.showLoadingError(errorResponse);
                callback.onError("Error");
            }
        });
        webService.execute();
    }
}

class WebService extends AsyncTask<String, Void, String> {

 //   private CityListContract.View mContext;
    private NetworkApiCallback onTaskDoneListener;


    public WebService(/*CityListContract.View context,*/ NetworkApiCallback onTaskDoneListener) {
       // this.mContext = context;
        this.onTaskDoneListener = onTaskDoneListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            URL mUrl = new URL(BuildConfig.BASEURL);
            HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);

            httpConnection.connect();

            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (onTaskDoneListener != null && s != null) {
            onTaskDoneListener.onSuccess(s);

        } else
            onTaskDoneListener.onError(s);
    }


    public interface NetworkApiCallback{
        void onSuccess(String  successResponse);
        void onError(String errorResponse);
    }
}

