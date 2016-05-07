package com.example.trim.contentprovider;

import android.util.Log;

/**
 * Created by Administrator on 2016/4/22.
 */
public class MyLog {
    private static final String TAG = "Trim";
    private static final boolean DEBUG = true;

    public static void i(String text){
        if (DEBUG)
            Log.i(TAG, text);
    }

    public static void d(String text){
        if (DEBUG)
            Log.d(TAG, text);
    }

    public static void e(String text){
        if (DEBUG)
            Log.e(TAG, text);
    }
}
