package com.iptv.unicom.payreqbean;

/**
 * classes:com.lexiang.pay.requestBean.UnipayExtReqBean
 *
 * @author lt
 * @date 2016/6/1
 * @time 11:01
 * @description1
 */
public class UnipayExtReqBean {
    /**
     * 订单号，由开发者自定义，但必须保证全局唯一。
      建议使用UUID随机数作为订单号，以免重复，建议不要使用伪随机数。(是)
     */
    public String tradeNo;
    /**
     * 开发者为商品分配的ID。(是)
     */
    public String productId;
    /**
     * 商品名称(是)
     */
    public String subject;
    /**
     * 商品描述（否）
     */
    public String desc;
    /**
     * 商品总价。（是）
     */
    public String amount;
    /**
     * 与amount对应的货币单位，
     * 使用ISO 4217规范中定义的3位字母形式编码（否）
     */
    public String currency;
    /**
     * 由开发者自定义，SDK将在回调（否）
     * 和服务器通知中透传该字段。
     */
    public String note;
    /**
     * 开发者给第三方应用分配的唯一应用ID。
     主要用于管理多个第三方应用的渠道商使用，如不涉及此场景可不填
     */
    public String thirdAppId;
    /**
     * 第三方应用的名称，用于交易显示和支付结果通知。（否）
     主要用于管理多个第三方应用渠道商使用，如不涉及此场景可不填。
     */
    public String thirdAppName;
    /**
     * 第三方应用的package name。（否）
     主要用于管理多个第三方应用渠道商使用，如不涉及此场景可不填
     */
    public String thirdAppPkgname;
    /**
     * 开发者在后台接收最终支付结果通知的HTTP URL。
     如果不需要接收后台通知可不填。（否）
     */
    public String notifyURL;
    
    
    
    
}
