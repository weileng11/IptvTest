package com.iptv.test.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.iptv.test.R;
import com.iptv.test.utils.Logger;
import com.iptv.test.utils.StaticUtils;
import com.iptv.test.utils.ToastUtils;
import com.iptv.unicom.UnicomAuth;
import com.iptv.unicom.UnicomPayInit;
import com.iptv.unicom.UnicomPurchase;
import com.iptv.unicom.payContants.UnicomContants;
import com.iptv.unicom.payinterface.UnicomPayAuthResultAble;
import com.iptv.unicom.payinterface.UnicomPayInItResultAble;
import com.iptv.unicom.payinterface.UnicomPayPurchaseResultAble;
import com.sdk.commplatform.entry.AuthResult;
import com.sdk.commplatform.entry.PayResult;
import com.sdk.commplatform.entry.ProductInfos;

/**
 * classes:com.iptv.test.activity.UnicomPayTestActivity
 * @author lt
 * @date 2016/6/21
 * @time 18:07
 * @description   联通支付测试
 */
public class UnicomPayTestActivity extends FragmentActivity {

    private UnicomPayInit unicomPayInit;
    private boolean intPay;

    /**
     * 省份id
     */
    private String provinceId = "";
    private UnicomAuth unicomAuth;
    private UnicomPurchase unicomPurchase;

    private String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unicom_test);

        initView();
    }

    public void initView() {
        //联通支付初始化 失败直接退出
        unicomPayInit = new UnicomPayInit(callback);
        unicomPayInit.init(UnicomPayTestActivity.this, StaticUtils.getAppInfo(UnicomPayTestActivity.this));
    }

    /**
     * 联通支付初始化回调接口
     */
    private UnicomPayInItResultAble callback = new UnicomPayInItResultAble() {

        @Override
        public void UnicomPayInitSuccess() {
            Logger.i("INFO","初始化成功");
            ToastUtils.showToastLong(UnicomPayTestActivity.this, "初始化成功");
            saveUser(true);
        }

        @Override
        public void UnicomPayInitFail() {
            saveUser(false);
        }
    };


    private void saveUser(boolean intPay) {
        this.intPay = intPay;
        if (intPay || true) {
            //初始化成功后开始执行鉴权
            unicomAuth = new UnicomAuth(mCallBackAuth);
            //1.productId 2.productType 3.contentId
            unicomAuth.authNew(UnicomPayTestActivity.this, UnicomContants.productId, UnicomContants.productType, UnicomContants.contentId);
        } else {
            ToastUtils.showToastShort(UnicomPayTestActivity.this, "初始化失败");
        }
    }


    /**
     * 鉴权回调接口
     * @param intPay
     */
    UnicomPayAuthResultAble mCallBackAuth=new UnicomPayAuthResultAble() {
        @Override
        public void UnicomPayAuthSuccess(int code) {
            if(code==0){
                ToastUtils.showToastLong(UnicomPayTestActivity.this,"鉴权成功"+code);
            }
        }

        @Override
        public void UnicomPayAuthFail(int code,AuthResult mAuthBean) {
            if(code!=0){
                //10020003  没有可订购产品
                //当错误码code=-1返回resultCode=10020001的时候，会出现省份的id
                //productInfos[0]
                Logger.i("INFO","鉴权失败");
                UnicomPayAuthFailErrorDataSave(code,mAuthBean);
            }
        }
    };

    /**
     * 鉴权失败的时候返回
     * @code -1
     *  mAuthBean.resultCode  10020001
     */
    public void UnicomPayAuthFailErrorDataSave(int code,AuthResult mAuthBean){
        if(code==-1){
            if(mAuthBean.resultCode!=null&&!mAuthBean.resultCode.equals("")){
                if(mAuthBean.resultCode.equals(UnicomContants.AUTHERROR_PRODUCTID)){
                    ProductInfos productBean=mAuthBean.productInfos[0];
                    Log.i("INFO", productBean + "==================商品的Id=========================");
                    provinceId=productBean.productId.split("@")[1];
                    //保存省份Id到sp
//                    SharedPreferencesUtils.saveString(context, ContansUtils.Commplatform_provinceid, provinceId);
                    //开始执行订购
                    unicomPurchase=new UnicomPurchase(unicomPayPurchaseResultAble);
                    //订单号
                    String mtradeNo =StaticUtils.GetRandomUUID();
                    //保存订单号用来取消订单
                    Log.i("INFO", productBean.productId + "==================商品的Id啦啦啦啦啦========================="+productBean.productDescription);
                    Logger.i("INFO","鉴权失败10020001");
                    unicomPurchase.UnicomPurchaseReqData(productBean, UnicomPayTestActivity.this,mtradeNo);
                }else{
                    Logger.i("INFO","鉴权失败111");
                    ToastUtils.showToastLong(UnicomPayTestActivity.this,"鉴权失败："+mAuthBean.resultCode);
                }
            }
        }else{
            Logger.i("INFO","鉴权失败22222");
            ToastUtils.showToastLong(UnicomPayTestActivity.this, "鉴权失败：" + code);
        }
    }

    /**
     * 联通订购回调接口
     * @param intPay
     */
    UnicomPayPurchaseResultAble unicomPayPurchaseResultAble=new UnicomPayPurchaseResultAble() {
        @Override
        public void UnicomPayPurchaseSuccess(PayResult mBean) {
            ToastUtils.showToastLong(UnicomPayTestActivity.this,"联通订购成功：");
        }

        @Override
        public void UnicomPayPurchaseFail(PayResult mBean) {
            ToastUtils.showToastLong(UnicomPayTestActivity.this,"联通订购失败：");
        }
    };
}
