package com.example.aggregate_methods.tools;

import android.util.Log;

import com.example.aggregate_methods.BuildConfig;

/**
 * CREATE BY liyang
 * ON 2021-01-26
 * SUPPLY : Thanks for watching
 */
public class Logger {
    public static void i(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            Log.i(tag, msg);

    }

    public static void v(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            Log.v(tag, msg);

    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            Log.d(tag, msg);

    }

    public static void w(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            Log.w(tag, msg);

    }

    public static void e(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG)
            Log.e(tag, msg);
    }
}
