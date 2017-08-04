package com.haidaoservice.lib.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.haidaoservice.lib.common.tool.DES3Util;
import com.haidaoservice.lib.common.tool.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author:davidinchina on 2017/7/24 10:42
 * email:davicdinchina@gmail.com
 * tip:
 */
public class LocalGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    LocalGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        ResponseBody newBody = ResponseBody.create(value.contentType(), value.string());
        String responseString = newBody.string();
        JSONObject result = new JSONObject();
        try {
            result = new JSONObject(responseString);
            if (null != result.getString("data")) {
                String data = (String) result.get("data");
                if (!"".equals(data)) {
                    data = DES3Util.decryptDES(data);
                    if (!"".equals(data) && !"null".equals(data) && null != data) {
                        LogUtil.error(data);
                        if (data.equals("[]")) {
                            data = "{}";
                        }
                        result.putOpt("data", new JSONObject(data));
                    } else {
                        result.putOpt("data", "");//添加空串数据
                    }
                } else {
                    result.putOpt("data", "");//添加空串数据
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonReader jsonReader = gson.newJsonReader(ResponseBody.create(newBody.contentType(),
                result.toString()).charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
