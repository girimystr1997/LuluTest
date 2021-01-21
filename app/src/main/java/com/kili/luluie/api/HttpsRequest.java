package com.kili.luluie.api;

import android.app.Activity;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class HttpsRequest {
    private String apis;
    private Context ctx;
    private Activity activity;


    public HttpsRequest(String apis, Activity activity) {
        this.apis = apis;
        this.activity  = activity;
    }

    public void stringGet(int s, Helper helper) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(s, apis, null, response -> {
            helper.backResponse(true,"Success",response);
        }, error -> {
            helper.backResponse(false,"Failed "+error,null);
        });
        requestQueue.add(jsonObjectRequest);
    }
}
