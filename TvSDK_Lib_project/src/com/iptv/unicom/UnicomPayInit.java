package com.iptv.unicom;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.iptv.unicom.payContants.UnicomContants;
import com.iptv.unicom.payinterface.UnicomPayInItResultAble;
import com.iptv.unicom.payreqbean.AppIDAndKeyReqBean;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.entry.AppInfo;
import com.sdk.commplatform.entry.ErrorCode;
import com.sdk.commplatform.listener.CallbackListener;

/**
 * classes:com.lexiang.pay.UnicomPayInit
 *
 * @author lt
 * @date 2016/6/1
 * @time 10:36
 * @description 联通支付初始化数据
 */
public class UnicomPayInit {

    public String TAG = "UnicomPayInit";

    Handler mHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UnicomContants.MPAYINIT_SUCCESS:
                    callback.UnicomPayInitSuccess();
                    break;
                case UnicomContants.MPAYINIT_FAIL:
                    callback.UnicomPayInitFail();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private UnicomPayInItResultAble callback;

    public UnicomPayInit() {
    }

    public UnicomPayInit(UnicomPayInItResultAble callback) {

        this.callback = callback;
    }

    /**
     * 初始化支付
     *
     * @param context
     */
    public void init(final Activity context, String appPackageName) {
        AppIDAndKeyReqBean payInitReqBean = new AppIDAndKeyReqBean(context, appPackageName);
        AppInfo appInfo = payInitReqBean.init();
        //标识参数，默认为0
        Commplatform.getInstance().Init(0,
                appInfo,
                new CallbackListener<Integer>() {
                    @Override
                    public void callback(final int paramInt, final Integer paramT) {
                        Log.d(TAG, "Init | callback, result=" + paramInt);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (paramInt == ErrorCode.COM_PLATFORM_SUCCESS) {
                                    mHandler.sendEmptyMessage(UnicomContants.MPAYINIT_SUCCESS);
                                } else {
                                    mHandler.sendEmptyMessage(UnicomContants.MPAYINIT_FAIL);
                                }
                            }
                        });
                    }
                });
    }

    /**
     * 获取用户的id
     *
     * @return
     */
    public String getUserId() {
        String userId = Commplatform.getInstance().getLoginUin();
        return userId;
    }
    public void destroy(){
        Commplatform.getInstance().destroy();
    }
}
