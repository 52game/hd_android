package com.haidaoservice.lib.common.tool;

import android.text.TextUtils;

/**
 * Created by zhangfei on 2017/3/31.
 */

public class StringUtils {

    public static String formatNullString(String s) {
        if (TextUtils.isEmpty(s))
            return "";
        else
            return s.equals("null") ? "" : s;
    }
}
