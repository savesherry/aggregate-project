package com.example.aggregateproject.okgo;

import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import com.example.aggregate_methods.base.BaseActivity;
import com.example.aggregate_methods.dialog.progress.DownloadDialog;
import com.example.aggregate_methods.tools.Logger;
import com.example.aggregateproject.R;
import com.example.aggregateproject.http.DownloadResponseListener;
import com.example.aggregateproject.http.HttpTools;
import com.example.aggregateproject.http.JsonResponseListener;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * CREATE BY LiYang
 * ON 2021-03-15
 * SUPPLY : Thanks for watching
 */
public class OkGoActivity extends BaseActivity {

    private Button requestButton, updateButton;

    private DownloadDialog dialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_okgo;
    }

    @Override
    protected void initView() {
        requestButton = findViewById(R.id.requestButton);
        updateButton = findViewById(R.id.updateButton);
    }

    @Override
    protected void initData() {
        dialog = new DownloadDialog(this);
    }

    @Override
    protected void setListener() {
        requestButton.setOnClickListener(v -> {
            String url = "http://192.168.1.120/business/app/login/dologin?";
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

        updateButton.setOnClickListener(v -> {
            String url = " http://218.27.1.157:9090/profile/upload/2021/03/10/a71a149c-dcf9-4e1d-95b9-c647cd092d7b.apk";
            HttpTools.download(this, url, Environment.getExternalStorageDirectory() + "/OKGO", "OkGo.apk", new DownloadResponseListener() {
                @Override
                public void onStart() {
                    dialog.setHeight(OkGoActivity.this);
                }

                @Override
                public void onProgress(String downloadLength, String totalLength, String downloadSpeed, int percentage) {
                    dialog.setProgressBar(percentage, "下载速度" + downloadSpeed + "，请您耐心等待！");
                }

                @Override
                public void onFinish(File file) {
                    if (file.exists())
                        Toast.makeText(OkGoActivity.this, "当前文件下载完毕", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(OkGoActivity.this, "当前文件下载失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

                @Override
                public void onFailure(String error) {
                    dialog.dismiss();
                }
            });
        });
    }

    @Override
    protected void setNerWork() {

    }


}
