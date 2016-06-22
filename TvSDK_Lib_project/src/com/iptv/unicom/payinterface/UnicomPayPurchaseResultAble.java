package com.iptv.unicom.payinterface;

import com.sdk.commplatform.entry.PayResult;

/**
 * classes:com.iptv.com.iptv.unicompay.uniconinterface.UnicomPayInItResultAble
 * @author lt
 * @date 2016/6/16
 * @time 16:23
 * @description   订购接口
 */
public interface UnicomPayPurchaseResultAble {
    
    void UnicomPayPurchaseSuccess(PayResult mBean);
    
    void UnicomPayPurchaseFail(PayResult mBean);
}
