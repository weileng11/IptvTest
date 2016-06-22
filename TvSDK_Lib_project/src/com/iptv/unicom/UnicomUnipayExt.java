package com.iptv.unicom;

import android.app.Activity;
import android.widget.Toast;

import com.iptv.unicom.payreqbean.UnipayExtReqBean;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.entry.ErrorCode;
import com.sdk.commplatform.entry.PayResult;
import com.sdk.commplatform.entry.Payment;
import com.sdk.commplatform.framework.util.StringUtil;
import com.sdk.commplatform.listener.CallbackListener;

/**
 * classes:com.lexiang.pay.UnicomUnipayExt
 *
 * @author lt
 * @date 2016/6/1
 * @time 11:00
 * @description 联通支付购买
 */
public class UnicomUnipayExt {

    /**
     * 保存支付需要的参数
     *
     * @param mUeI
     * @return
     */
    private Payment getBuyInfo(UnipayExtReqBean mUeI) {   //校验商品信息
        Payment buyInfo = new Payment();
        buyInfo.setTradeNo(mUeI.tradeNo);
        buyInfo.setProductId(mUeI.productId);
        buyInfo.setSubject(mUeI.subject);
        buyInfo.setAmount(StringUtil.isEmpty(mUeI.amount) ? 0 : Double.parseDouble(mUeI.amount));
        buyInfo.setDesc(mUeI.desc);
        buyInfo.setCurrency(mUeI.currency);
        buyInfo.setNote(mUeI.note);
        buyInfo.setThirdAppId(mUeI.thirdAppId);
        buyInfo.setThirdAppName(mUeI.thirdAppName);
        buyInfo.setThirdAppPkgname(mUeI.thirdAppPkgname);
        buyInfo.setNotifyURL(mUeI.notifyURL);
        return buyInfo;
    }

    /**
     * 执行支付命令
     *
     * @param activity
     * @param mUeI
     * @return
     */
    private int pay(final Activity activity, UnipayExtReqBean mUeI) {
//        activity.isPaying = true;
        Payment buyInfo = getBuyInfo(mUeI);
        int ret =
                Commplatform.getInstance().uniPayExt(buyInfo,
                        activity,
                        new CallbackListener<PayResult>() {

                            @Override
                            public void callback(final int arg0, PayResult arg1) {
                                activity.runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
//                                        activity.isPaying = false;
                                        if (arg0 == ErrorCode.COM_PLATFORM_SUCCESS) {
                                            Toast.makeText(activity,
                                                    "成功",
                                                    Toast.LENGTH_SHORT).show();
                                        } else if (arg0 == ErrorCode.COM_PLATFORM_ERROR_PAY_FAILURE) {
                                            Toast.makeText(activity,
                                                    "失败",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(activity,
                                                    "Purchase failed. Error code:" + arg0,
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });

        if (ret == 0) {
            return 0;
        } else {
            //返回错误，即支付过程结束
//            activity.isPaying = false;
            return -1;
        }
    }

}
