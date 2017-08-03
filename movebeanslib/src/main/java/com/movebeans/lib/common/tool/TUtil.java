package com.movebeans.lib.common.tool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hpw on 16/10/28.
 */

public class TUtil
    {
    public static <T> T getT(Object o, int i) {
        try {
            Type superclass = o.getClass().getGenericSuperclass();
            if (superclass instanceof ParameterizedType) {
                return ((Class<T>) ((ParameterizedType) superclass)
                        .getActualTypeArguments()[i])
                        .newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

