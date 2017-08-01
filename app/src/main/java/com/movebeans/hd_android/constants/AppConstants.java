package com.movebeans.hd_android.constants;

import java.io.File;

/**
 * Created by zhangfei on 2017/4/11.
 */

public class AppConstants {

    /**
     * 应用程序主目录
     */
    public final static String ROOT_DIRECTORY = "MoveBeans";

    /**
     * apk下载储存目录
     **/
    public final static String APK_DIRECTORY = ROOT_DIRECTORY + File.separator + "apk";

    /**
     * apk文件的文件名
     */
    public final static String APK_STORAGE_NAME = ROOT_DIRECTORY + "_%s.apk";

    /**
     * 应用程序上传视频目录
     */
    public final static String UPLOAD_VIDEO_DIRECTORY = ROOT_DIRECTORY + "/video";

    /**
     * 应用程序上传语音目录
     */
    public final static String UPLOAD_VOICE_DIRECTORY = ROOT_DIRECTORY + "/voice";

    /**
     * 上传的最大头像尺寸
     **/
    public final static int UPLOAD_MAX_HEADER_WIDTH = 500;
}
