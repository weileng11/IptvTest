package com.iptv.unicom;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.huawei.ambp.ability.log.Logger;
import com.iptv.unicom.payContants.UnicomContants;
import com.iptv.unicom.payinterface.UnicomPayPurchaseResultAble;
import com.iptv.unicom.payreqbean.PurchaseReqBean;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.entry.CyclePayment;
import com.sdk.commplatform.entry.ErrorCode;
import com.sdk.commplatform.entry.PayResult;
import com.sdk.commplatform.entry.ProductInfos;
import com.sdk.commplatform.listener.CallbackListener;

/**
 * classes:com.lexiang.pay.UnicomPurchase
 * @author lt
 * @date 2016/6/2
 * @time 9:54
 * @description 联通支付订购
 * 3.3 购买周期性计费商品
 */
public class UnicomPurchase {

    private static String TAG = "UnicomPurchase";

    private static PayResult mPayBean;
    private static UnicomPayPurchaseResultAble mPlayPurchaseResultAble;
    
    private static String tradeNoRandom="";

    public UnicomPurchase(UnicomPayPurchaseResultAble mPlayPurchaseAble) {
        mPlayPurchaseResultAble = mPlayPurchaseAble;
    }

    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UnicomContants.MPAYPURCHASE_SUCCESS:
                    mPlayPurchaseResultAble.UnicomPayPurchaseSuccess(mPayBean);
                    break;
                case UnicomContants.MPAYPURCHASE_FAIL:
                    //可以根据resultCode去提示对应的错误信息(如：1002003)
                    mPlayPurchaseResultAble.UnicomPayPurchaseFail(mPayBean);
                    break;
            }
            super.handleMessage(msg);
        }
    };


    /**
     * 联通订购请求数据方法
     * @param 
     */
    public static void UnicomPurchaseReqData(ProductInfos mProductInfo,Activity activity,String mTradeNo){
        PurchaseReqBean pReqBean=new PurchaseReqBean();
        pReqBean.tradeNo="lialdskjfsdlja7778787"; //随机生成订单号
        pReqBean.productId="txblyby025@201";  //鉴权失败后分配的商品Id
        pReqBean.note="test1"; //n   
        pReqBean.thirdAppId="daoran0011";//n
        pReqBean.thirdAppName="com.daoran.demo";//n
        pReqBean.thirdAppPkgname="daoran111";//n
        pReqBean.notifyURL="http://192.168.11.49:8080/OneServer/sdk/login";//n  //开发者在后台接收最终支付结果通知的HTTP URL。
        pReqBean.unsubNotifyURL="http://192.168.11.49:8080/OneServer/sdk/login";//n

        purchase(pReqBean, activity);
    }
    
    /**
     * 传入订购的请求参数
     *
     * @param prBean
     * @param activity
     * @return
     */
    public static CyclePayment getCyclePayment(PurchaseReqBean prBean, Activity activity) {
        //校验商品信息
        if (prBean.tradeNo == null || prBean.tradeNo.trim().equals("".trim())) {
            Toast.makeText(activity,
                    "没有订单号",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        CyclePayment cyclePayment = new CyclePayment();
        cyclePayment.setTradeNo(prBean.tradeNo);
        cyclePayment.setProductId(prBean.productId);
        cyclePayment.setNote(prBean.note);
        cyclePayment.setThirdAppId(prBean.thirdAppId);
        cyclePayment.setThirdAppName(prBean.thirdAppName);
        cyclePayment.setThirdAppPkgname(prBean.thirdAppPkgname);
        cyclePayment.setNotifyURL(prBean.notifyURL);
        cyclePayment.setUnsubNotifyURL(prBean.notifyURL);
        return cyclePayment;
    }


    /**
     * 执行订购请求
     *
     * @param prBean
     * @param activity
     * @return
     */
    public static int purchase(PurchaseReqBean prBean, final Activity activity) {
//        activity.isPaying =true;
        CyclePayment buyInfo = getCyclePayment(prBean, activity);
        int ret = Commplatform.getInstance().subsPay(buyInfo,activity, new CallbackListener<PayResult>() {
                            @Override
                            public void callback(final int arg0, final PayResult payResult) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        activity.isPaying = false;
                                        if (arg0 == ErrorCode.COM_PLATFORM_SUCCESS) {
                                            Logger.d(TAG, "cancelPayResult : " + payResult);
                                            mPayBean = payResult;
                                            mHandler.sendEmptyMessage(UnicomContants.MPAYPURCHASE_SUCCESS);
                                            Toast.makeText(activity, "成功", Toast.LENGTH_SHORT).show();
                                        } else if (arg0 == ErrorCode.COM_PLATFORM_ERROR_PAY_FAILURE) {
                                            mPayBean = payResult;
                                            mHandler.sendEmptyMessage(UnicomContants.MPAYPURCHASE_FAIL);
                                            Toast.makeText(activity, "失败", Toast.LENGTH_SHORT).show();
                                        } else {
                                            mHandler.sendEmptyMessage(UnicomContants.MPAYPURCHASE_FAIL);
                                            Toast.makeText(activity, "Purchase failed. Error code:" + arg0, Toast.LENGTH_SHORT).show();
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


    /**
     * 3.4 取消订购周期性计费商品
     * @param tradeNo 订单id
     * @param activity
     * @return
     */
    public int cancelCyclePay(String tradeNo, final Activity activity) {
//        activity.isPaying =true;
        int ret =
                Commplatform.getInstance().cancelCyclePay(tradeNo,
                        activity,
                        new CallbackListener<PayResult>() {

                            @Override
                            public void callback(final int arg0, final PayResult arg1) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        activity.isPaying = false;
                                        if (arg0 == ErrorCode.COM_PLATFORM_SUCCESS) {
                                            Logger.d(TAG, "cancelPayResult : " + arg1);
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
            //返回错误，取消订单过程结束
//            activity.isPaying = false;
            return -1;
        }
    }

    

}
