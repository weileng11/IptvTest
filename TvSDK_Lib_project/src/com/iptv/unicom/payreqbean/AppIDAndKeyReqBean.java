package com.iptv.unicom.payreqbean;

import android.content.Context;
import android.text.TextUtils;

import com.sdk.commplatform.entry.AppInfo;

/**
 * classes:com.lexiang.pay.bean.AppIDAndKeyReqBean
 *
 * @author lt
 * @date 2016/6/1
 * @time 10:50
 * @description1
 */
public class AppIDAndKeyReqBean {
    private final Context context;
    private final String appPackageName;
    /**
     * 由运营组提供的应用ID(必填)
     */
    public String txb_appid = "drtxbly";
    /**
     * 由运营组提供的应用Key(必填)
     */
    public String txb_appkey = "gzdrjf";

    public String txb_PackageName = "com.iptv.test";
    /**
     * 版本检查结果，默认为0（否）
     */
    public int checkStatus = 0;

    public AppIDAndKeyReqBean(Context context, String appPackageName) {
        this.context = context;
        this.appPackageName = appPackageName;
    }

    public AppInfo init() {
        if (TextUtils.isEmpty(appPackageName)) {
            return null;

        }
        String appkey = "";
        String appid = "";
        AppInfo appInfo = new AppInfo();
        if (txb_PackageName.equals(appPackageName)) {
            //乐享
            appid = txb_appid;
            appkey = txb_appkey;
        } else if (false) {

        } else {
            return null;
        }
        appInfo.setAppId(appid);
        appInfo.setAppKey(appkey);
        appInfo.setCtx(context);
        appInfo.setVersionCheckStatus(checkStatus);
        return appInfo;
    }
}
