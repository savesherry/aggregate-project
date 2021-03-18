package com.example.aggregateproject.okgo;

import android.widget.Button;

import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.tools.Logger;
import com.example.aggregateproject.R;
import com.example.aggregateproject.http.HttpTools;
import com.example.aggregateproject.http.JsonResponseListener;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public class OkGoActivity extends BaseActivity {

    private Button requestButton;
    private String url = "http://192.168.1.120/business/app/login/dologin?";

    @Override
    protected int getLayout() {
        return R.layout.activity_okgo;
    }

    @Override
    protected void initView() {
        requestButton = findViewById(R.id.requestButton);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListener() {
        requestButton.setOnClickListener(v -> {
            HashMap<String, Object> params = new HashMap<>();
            params.put("login_name", "admin");
            params.put("password", "123456");
            HttpTools.post(this, url, params, new JsonResponseListener() {
                @Override
                public void onSuccess(JSONObject response) {
                    Logger.e(TAG, response.toString());

                }

                @Override
                public void onFailure(String result) {
                    Logger.e(TAG, result);
                }
            });

        });
    }

    @Override
    protected void setNerWork() {

    }
}
