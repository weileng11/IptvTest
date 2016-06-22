package com.iptv.unicom.payinterface;

import com.sdk.commplatform.entry.AuthResult;

/**
 * classes:com.iptv.com.iptv.unicompay.uniconinterface.UnicomPayInItResultAble
 * @author lt
 * @date 2016/6/16
 * @time 16:23
 * @description   鉴权接口
 */
public interface UnicomPayAuthResultAble {
    
    void UnicomPayAuthSuccess(int code);
    
    void UnicomPayAuthFail(int code, AuthResult mAuthBean);
}
