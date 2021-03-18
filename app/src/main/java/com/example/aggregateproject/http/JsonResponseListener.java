package com.example.aggregateproject.http;


import org.json.JSONObject;

public interface JsonResponseListener {

    void onSuccess(JSONObject response);

    void onFailure(String result);
}
