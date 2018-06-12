package com.root.ashad.appmaker.news;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by root on 13/3/18.
 */

public class mysingleton {

    private static mysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mcontex;

    private mysingleton(Context context) {
        mcontex = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mcontex.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized mysingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new mysingleton(context);

        }
        return mInstance;

    }

    public void addToRequestque(Request request) {
        requestQueue.add(request);
    }
}