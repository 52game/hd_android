package com.movebeans.lib.net;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 字符编码过滤器
 */

public class DecoderInterceptor implements Interceptor {


    public DecoderInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        FormBody.Builder newBody = new FormBody.Builder();
        if (oldRequest.body() instanceof FormBody) {
            FormBody body = (FormBody) oldRequest.body();
            int size = body.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    String value = URLDecoder.decode(body.encodedValue(i), "UTF-8");
                    newBody.add(body.encodedName(i), value);
                }
            }
        }
        Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), newBody.build()).build();
        if (oldRequest.body() instanceof MultipartBody) {
            return chain.proceed(oldRequest);
        } else {
            return chain.proceed(newRequest);
        }
    }
}