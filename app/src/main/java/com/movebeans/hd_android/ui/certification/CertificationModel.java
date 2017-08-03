package com.movebeans.hd_android.ui.certification;

import com.movebeans.lib.base.BaseModel;

/**
 * ClassName: CertificationModel
 * Description: say something
 * Creator: chenwei
 * Date: 2017/8/2 14:42
 * Version: 1.0
 */
public class CertificationModel implements BaseModel {
    /**
     * 审核通过
     */
    public final static int APPROVE = 0;
    /**
     * 审核中
     */
    public final static int REVIEW = 1;
    /**
     * 过期
     */
    public final static int OUT_OF_DATE = 2;
    /**
     * 审核未通过
     */
    public final static int REJECT = 3;

    /**
     * 认证状态
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
