package com.haidaoservice.yhs.ui.my.setting.helpAFeedback;

/**
 * author:davidinchina on 2017/8/8 09:59
 * email:davicdinchina@gmail.com
 * version:1.0.0
 * des:
 */
public class HelpPresenter extends HelpContact.HelpContactPresenter {
    @Override
    public <M> M getmModel() {
        return (M) new HelpModel();
    }
    @Override
    public void query() {
    }
}
