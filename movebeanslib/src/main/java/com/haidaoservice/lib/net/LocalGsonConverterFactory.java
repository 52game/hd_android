package com.haidaoservice.lib.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author:davidinchina on 2017/7/24 10:31
 * email:davicdinchina@gmail.com
 * tip:带解码的Gson转型工厂
 */
public final class LocalGsonConverterFactory extends Converter.Factory{
    public static LocalGsonConverterFactory create() {
        return create(new Gson());
    }
    public static LocalGsonConverterFactory create(Gson gson) {
        return new LocalGsonConverterFactory(gson);
    }

    private final Gson gson;

    private LocalGsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new LocalGsonResponseBodyConverter<>(gson, adapter);
    }
}
