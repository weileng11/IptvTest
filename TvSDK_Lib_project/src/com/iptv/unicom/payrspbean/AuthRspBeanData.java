package com.iptv.unicom.payrspbean;

import java.util.List;

/**
 * classes:com.lexiang.pay.payresponebean.AuthRspBeanData
 *
 * @author lt
 * @date 2016/6/2
 * @time 16:28
 * @description  鉴权返回响应数据
 */
public class AuthRspBeanData {

    /**
     * 鉴权结果描述。
     */
    public String description;
    /**
     * 鉴权通过订购关系有效期,格式：yyyyMMddHHmmss。
     为空表示永久有效。 
     */
    public String expiredTime;
    /**
     * 可订购商品列表，鉴权失败时返回。
     */
    public List<ProductInfo> productInfos; 

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public class ProductInfo{
        /**
         * 运营组配置的商品ID，返回商品列表时调用订购接口使用此字段ID。 是
         */
        public String productId;
        /**
         * 商品名称。
         */
        public String productName;
        /**
         * 商品描述。
         */
        public String productDescription;
        /**
         * 商品总价。
         */
        public String price;
    }
    
    /**
     * 鉴权通过时的返回订单号，可用于调用cancelCyclePay接口。 是
     */
    public String tradeNo;
    /**
     * expiredTime是否是测试支付，测试支付产生的消费流水，将不参与最终的分成结算：
     0：正常支付
     1：测试支付 是
     */
    public String isTest;
   




}
