package com.demo.cityIno.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Rishabh.
 */

public final class Utility {

    private Utility() {
        throw new IllegalStateException("Cannot Instantiate");
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
