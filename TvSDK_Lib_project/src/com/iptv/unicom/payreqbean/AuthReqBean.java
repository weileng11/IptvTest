package com.iptv.unicom.payreqbean;

/**
 * classes:com.lexiang.pay.bean.AuthReqBean
 *
 * @author lt
 * @date 2016/6/1
 * @time 10:52
 * @description 11
 */
public class AuthReqBean {
    /**
     * 开发者为商品分配的ID
     */
    public String productId ;
    /**
     * 商品类型，取值如下：
      1：一次性支付商品类型
      2：包周期支付商品类型
     */
    public String productType;
    /**
     * 运营组提供的内容ID
     */
    public String contentId;
}
