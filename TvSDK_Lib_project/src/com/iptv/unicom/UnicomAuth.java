package com.iptv.unicom;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.iptv.unicom.payContants.UnicomContants;
import com.iptv.unicom.payinterface.UnicomPayAuthResultAble;
import com.iptv.unicom.payreqbean.AuthReqBean;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.entry.AuthResult;
import com.sdk.commplatform.entry.ErrorCode;
import com.sdk.commplatform.framework.util.StringUtil;
import com.sdk.commplatform.listener.CallbackListener;

/**
 * classes:com.lexiang.pay.UnicomAuth
 *
 * @author lt
 * @date 2016/6/1
 * @time 10:41
 * @description 支付认证
 * 3.2 检查订购关系有效性1
 */
public class UnicomAuth {

    public static String TAG = "UnicomAuth";

    private static AuthResult mAuthBean;
    private static int mCode=0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UnicomContants.MPAYAUTO_SUCCESS:
                    mPlayAuthResultAble.UnicomPayAuthSuccess(mCode);
                    break;
                case UnicomContants.MPAYAUTO_FAIL:
                    //可以根据resultCode去提示对应的错误信息(如：1002003)
                    mPlayAuthResultAble.UnicomPayAuthFail(mCode,mAuthBean);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private UnicomPayAuthResultAble mPlayAuthResultAble;

    public UnicomAuth(UnicomPayAuthResultAble mPlayAuthAble) {
        mPlayAuthResultAble = mPlayAuthAble;
    }

    /**
     * 各种参数
     *
     * @param content
     * @param mAi
     */
    public void sendAuthRequest(final Activity content, AuthReqBean mAi) {
        if (StringUtil.isEmpty(mAi.contentId)) {
            authOld(content, mAi.productId, mAi.productType);
        } else {
            authNew(content, mAi.productId, mAi.productType, mAi.contentId);
        }
    }

    public void authOld(final Activity content, String productId, String productType) {
        Commplatform.getInstance().authPermission(productId,
                productType,
                new CallbackListener<AuthResult>() {
                    @Override
                    public void callback(final int code, final AuthResult result) {
                        Log.d(TAG, "Auth | callback, result=" + result);
                        content.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == ErrorCode.COM_PLATFORM_SUCCESS) {
                                    Toast.makeText(content, "成功",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(content, "失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }

    public void authNew(final Activity content, String productId, String productType, String contentId) {
        Commplatform.getInstance().authPermission(productId,
                productType,
                contentId,
                new CallbackListener<AuthResult>() {

                    @Override
                    public void callback(final int code, final AuthResult result) {
                        Log.d(TAG, "成功啦========================= result=" + result);
                        content.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == ErrorCode.COM_PLATFORM_SUCCESS) {
                                    Log.d(TAG, "成功啦=========================" + result);
                                    mCode=code;
                                    mHandler.sendEmptyMessage(UnicomContants.MPAYAUTO_SUCCESS);
                                } else {
                                    Log.d(TAG, "失败啦========================" + result);
                                    mAuthBean=result;
                                    mCode=code;
                                    mHandler.sendEmptyMessage(UnicomContants.MPAYAUTO_FAIL);
                                }
                            }
                        });
                    }
                });
    }

}
