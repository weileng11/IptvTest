package com.iptv.unicom.payreqbean;

/**
 * classes:com.lexiang.pay.payRequestBean.PurchaseReqBean
 *
 * @author lt
 * @date 2016/6/2
 * @time 9:58
 * @description1
 */
public class PurchaseReqBean {

    /**
     * 订单号，由开发者自定义，但必须保证全局唯一。
     建议使用UUID随机数作为订单号，
     以免重复，建议不要使用伪随机数（是）
     */
    public String tradeNo;
    /**
     * 开发者为商品分配的ID(是)
     */
    public String productId;
    /**
     * 由开发者自定义，SDK将在回调和服务器通知中透传该字段（否）
     */
    public String note;
    /**
     * 开发者给第三方应用分配的唯一应用ID。
     主要用于管理多个第三方应用的渠道商使用，如不涉及此场景可不填
     */
    public String thirdAppId;
    /**
     * 第三方应用的名称，用于交易显示和支付结果通知。
     主要用于管理多个第三方应用的渠道商使用，如不涉及此场景可不填。
     */
    public String thirdAppName;
    /**
     * 第三方应用的package name。
     主要用于管理多个第三方应用的渠道商使用，如不涉及此场景可不填。
     */
    public String thirdAppPkgname;
    /**
     * 开发者在后台接收最终支付结果通知的HTTP URL。
     如果不需要接收后台通知可不填。
     */
    public String notifyURL;
    /**
     * 开发者在后台接收商品退订结果通知的HTTP URL。
     如果不需要接收后台通知可不填。
     */
    public String unsubNotifyURL;
}
