package com.movebeans.hd_android.api;


import android.icu.util.VersionInfo;

import com.movebeans.hd_android.ui.user.User;
import com.movebeans.lib.base.JsonEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 请求接口类
 * <p>
 * Created by zhangfei on 2017/3/22.
 */

public interface HttpServerApi {

    String start = "";
    String end = ".json";


    /**---------------------通用模块---------------------------**/
    /**
     * 版本更新接口
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/version/checkVersion" + end)
//    Observable<JsonEntity<VersionInfo>> checkUpdate(@Field(value = "params", encoded = false) String params);
    @GET(start + "/version/checkVersion" + end)
    Observable<JsonEntity<VersionInfo>> checkVersion();

//    /**
//     * 上传文件接口
//     *
//     * @param file 上传文件
//     * @return
//     */
//    @Multipart
//    @POST(start + "/common/uploadFile" + end)
//    Observable<JsonEntity<Upload>> uploadFile(@Part MultipartBody.Part file);



    /**---------------------用户模块---------------------------**/
    /**
     * 登录
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "user/login" + end)
//    Observable<JsonEntity<User>> login(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/login" + end)
    Observable<JsonEntity<User>> login();

    /**
     * 第三方登录
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "user/thirdLogin" + end)
//    Observable<JsonEntity<User>> thirdLogin(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/thirdLogin" + end)
    Observable<JsonEntity<User>> thirdLogin();

    /**
     * 注册
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/register" + end)
//    Observable<JsonEntity<User>> register(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/register" + end)
    Observable<JsonEntity> register();

    /**
     * 获取验证码
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/getVCode" + end)
//    Observable<JsonEntity> getVCode(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/getVCode" + end)
    Observable<JsonEntity> getVCode();


    /**
     * 验证码检测
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/checkVCode" + end)
//    Observable<JsonEntity> checkVCode(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/checkVCode" + end)
    Observable<JsonEntity> checkVCode();

    /**
     * 重设密码
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/forgetPassword" + end)
//    Observable<JsonEntity> forgetPassword(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/forgetPassword" + end)
    Observable<JsonEntity> forgetPassword();

    /**
     * 获取用户资料
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/userInfo" + end)
//    Observable<JsonEntity> userInfo(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/userInfo" + end)
    Observable<JsonEntity<User>> userInfo();

    /**
     * 修改密码
     *
     * @param params 参数集合字符串
     * @return
     */
//    @FormUrlEncoded
//    @POST(start + "/user/modifyPassword" + end)
//    Observable<JsonEntity> modifyPassword(@Field(value = "params", encoded = false) String params);
    @GET(start + "/user/modifyPassword" + end)
    Observable<JsonEntity> modifyPassword();

}
