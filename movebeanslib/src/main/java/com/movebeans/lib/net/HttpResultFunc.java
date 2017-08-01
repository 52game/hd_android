package com.movebeans.lib.net;


import com.movebeans.lib.base.JsonEntity;
import com.movebeans.lib.common.tool.LogUtil;

import rx.functions.Func1;

/**
 * ClassName: HttpResultFunc
 * Description: say something
 * Creator: chenwei
 * Date: 16/8/27 17:13
 * Version: 1.0
 */
public class HttpResultFunc<T> implements Func1<JsonEntity<T>, T> {
    @Override
    public T call(JsonEntity<T> tJsonEntity) {
        LogUtil.error(tJsonEntity.getCode() + "");
        if (tJsonEntity.getCode() != 1) {//1为成功
            throw new ApiException(tJsonEntity.getCode(), tJsonEntity.getMessage());
        }
        return  tJsonEntity.getData();
    }
}
