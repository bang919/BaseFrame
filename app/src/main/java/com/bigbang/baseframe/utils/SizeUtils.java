package com.bigbang.baseframe.utils;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/18.
 */

public class SizeUtils {
    /**
     * 判断字符串是否是整数
     * +xxx,xxx,-xxx,+0,-0,0都算作整数
     *
     * @param str
     * @return true=>是;false=>不是
     */
    public static boolean strIsInteger(String str) {
        if (TextUtils.isEmpty(str))
            return false;
        Pattern p = Pattern.compile("((-|\\+)?[1-9]\\d*)|((-|\\+)?0)");
        Matcher m = p.matcher(str);
        if (m.matches())
            return true;
        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
