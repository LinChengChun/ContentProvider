package com.example.trim.contentprovider;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/24.
 */
public class CustomToast {

    private Context mContext = null;
    private static int mOldMsgId;
    private static String mOldMsg;
    protected static Toast mToast = null;
    private static long mOneTime = 0;
    private static long mTwoTime = 0;
    private static TextView mCotent = null;

    public static void showToast(Context context, int lenth, int resid) {
        if (mToast == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            mCotent = (TextView) view.findViewById(R.id.toast_content);
            mCotent.setText(resid);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(lenth);
            mToast.setView(view);
            mToast.show();
            mOneTime = System.currentTimeMillis();
        } else {
            mTwoTime = System.currentTimeMillis();
            if (resid == mOldMsgId) {
                if (mTwoTime - mOneTime > Toast.LENGTH_LONG) {
                    mToast.show();
                }
            } else {
                mOldMsgId = resid;
                mCotent.setText(resid);
                mToast.show();
            }
        }
        mOneTime = mTwoTime;
    }

    public static void showToast(Context context, int lenth, String info) {
        if (mToast == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast, null);
            mCotent = (TextView) view.findViewById(R.id.toast_content);
            mCotent.setText(info);
            mToast = new Toast(context);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setDuration(lenth);
            mToast.setView(view);
            mToast.show();
            mOneTime = System.currentTimeMillis();
        } else {
            mTwoTime = System.currentTimeMillis();
            if (info.equals(mOldMsg)) {
                if (mTwoTime - mOneTime > Toast.LENGTH_LONG) {
                    mToast.show();
                }
            } else {
                mOldMsg = info;
                mCotent.setText(info);
                mToast.show();
            }
        }
        mOneTime = mTwoTime;
    }

    public static void toast(Context context, String str) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(str);
        mToast.show();
    }
}
