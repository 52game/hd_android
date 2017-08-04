package com.haidaoservice.yhs.ui.user.view.code;

import com.haidaoservice.yhs.base.PublicParams;
import com.haidaoservice.lib.common.tool.RequestParamsUtils;
import com.haidaoservice.lib.net.SimpleSubscriber;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by zhangfei on 2017/3/29.
 */

public class CodePresenter extends CodeContract.CodePresenter {




    /**
     * 默认倒计时长
     **/
    public final int DEFAULT_CHECK_TIME_LENGTH = 60;

    private int checkTimeLength = DEFAULT_CHECK_TIME_LENGTH;

    private Subscription timerSubscription;
    @Override
    public <M> M getmModel() {
        return (M) new CodeModel();
    }

    @Override
    public void getVCode(String phone, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", type);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRxManager.add(mModel.getVCode(params).subscribe(new SimpleSubscriber() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Object o) {
                super.onNext(o);
                startCheckTimer();
                mView.checkButtonDisabled();
                mView.getSuccess();
            }
        }));

    }

    @Override
    public void checkVCode(String phone, int type, String VCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("type", type);
        map.put("VCode", VCode);
        String params = "";
        try {
            params = RequestParamsUtils.buildRequestParams(new PublicParams(mContext).createParams(), map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mRxManager.add(mModel.checkVCode(params).subscribe(new SimpleSubscriber() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Object o) {
                super.onNext(o);
                mView.checkSuccess();
            }
        }));
    }

    @Override
    public void startCheckTimer() {
        checkTimeLength = DEFAULT_CHECK_TIME_LENGTH;
        mRxManager.add(timerSubscription = Observable.interval(0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        checkTimeLength--;
                        mView.updateCountdown(checkTimeLength);
                        if (checkTimeLength <= 0) {
                            endCheckTimer();
                            mView.resumeCheckButton();
                        }
                    }
                }));
    }

    @Override
    public void endCheckTimer() {
        timerSubscription.unsubscribe();
    }
}
