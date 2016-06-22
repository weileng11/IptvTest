package com.iptv.unicom.payrspbean;

/**
 * classes:com.lexiang.pay.payresponebean.PurchaseRspBeanData
 *
 * @author lt
 * @date 2016/6/2
 * @time 16:38
 * @description
 */
public class PurchaseRspBeanData {
    /**
     * 0：支付成功。
     1：订单处理中（最终结果以后台通知为准，如有必要由应用调用queryPayment做漏单查询）。
     2：支付失败，余额不足。
     3：支付失败，用户主动取消支付。
     9：支付失败，其它错误。(是)
     */
    public String tradeStatus;
    /**
     * 发起支付的用户ID。
     如果用户未登录，此ID为空
     */
    public String uin;
    /**
     * 返回平台生成的消费流水号。
     当开发者需要与平台进行详单对账时，需要提供平台的流水号信息用于比对
     */
    public String ConsumeStreamId;
    /**
     * 用户需要支付的费用。
     存在折扣支付场景时（如对于VIP用户），
     用户需要支付的费用可能与商品定价不同；
     此字段为折扣后实际需要支付的费用。
     */
    public String chargeAmount;
    public String chargeAmountIncVAT;
    public String chargeAmountExclVAT;
    public String share;
    public String isTest;
    
    
}
