package com.bigbang.baseframe.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by bigbang on 2018/3/29.
 */

public class FileUtils {

    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
}
