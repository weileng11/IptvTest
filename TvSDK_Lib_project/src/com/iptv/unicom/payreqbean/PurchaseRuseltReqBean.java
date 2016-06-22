package com.iptv.unicom.payreqbean;

/**
 * classes:com.lexiang.pay.payRequestBean.PurchaseRuseltReqBean
 *
 * @author lt
 * @date 2016/6/2
 * @time 16:08
 * @description  4.1 接收支付购买结果接口1
 */
public class PurchaseRuseltReqBean {
    /**
     * 表示支付结果通知，默认为100 (是)
     */
    public String Act;
    /**
     * SDK分配给开发者的应用ID。(是)
     */
    public String AppId;
    /**
     * 开发者给第三方应用分配的唯一应用ID。
     */
    public String ThirdAppId;
    /**
     * 发起支付的用户ID。
     如果用户未登录，此ID为空
     */
    public String Uin;
    /**
     * 平台生成的消费流水号。
     当开发者需要与平台进行详单对账时，
     需要提供平台的流水号信息用于比对。(是)
     */
    public String ConsumeStreamId;

    /**
     * 应用在调用支付接口时传入的订单号。(是)
     */
    public String TradeNo;
    /**
     * 应用在调用支付接口时传入的商品名称(是)
     */
    public String Subject;
    /**
     * 应用在调用支付接口时传入的商品价格(是)
     */
    public String Amount;
    /**
     * 用户需要支付的费用。
     存在折扣支付场景时（如对于VIP用户），
     用户需要支付的费用可能与商品定价不同
     ；此字段为折扣后实际需要支付的费用(是)
     */
    public String ChargeAmount;

    /**
     * 用户实际支付的费用，包含VAT。
     在有消费税（VAT）的国家，用户在购买商品时需要额外支付消费税。
     有的国家显示的商品价格为含税价格，有的国家显示为不含税价格，
     由支付渠道在从用户账户中扣费时同时扣除消费税。
     chargeAmountIncVAT/chargeAmountExclVAT分别为用户实际需要支付的含税价和不含税价。
     对于商品价格即为含税价格的国家，chargeAmountIncVAT=chargeAmount；
     对于商品价格为不含税价格的国家，chargeAmountExclVAT=chargeAmount。(是)
     */
    public String ChargeAmountIncVAT;
    /**
     * 用户实际支付的费用，不包含VAT。
     chargeAmountExclVAT的含义见上
     。最终对账结算时的收入，应当以chargeAmountExclVAT为准。(是)
     */
    public String ChargeAmountExclVAT;
    /**
     * 用户所属国家，使用ISO 3166-1规范定义的2位字母编码。(是)
     */
    public String Country;
    /**
     * 应用在调用支付接口时传入的商品名称用户支付使用的实际货币，
     * 使用ISO 4217规范中定义的3位字母形式编码。
     用户支付时实际使用的货币可能与购买请求中传入的货币不同（
     比如：商品以本地货币定价，而用户选择了一些只支持美金的支付渠道时）
     ，最终分成结算时将以实际支付货币为准。(是)
     */
    public String Currency;

    /**
     * 商户按照分成比例得到的收入。
     实际收入可能因为汇率、坏账等因素变化，应以对账单中的数值为准。(是)
     */
    public String Share;
    /**
     * 支付时应用传入SDK的透传信息。(是)
     */
    public String Note;
    /**
     * 支付结果：
     Completed:支付成功
     Failed:失败
     Canceled:取消支付(是)
     Expired:处理中
     */
    public String TradeStatus;
    /**
     * 创建时间(yyyy-MM-dd HH:mm:ss)
     */
    public String CreateTime;
    /**
     * 是否是测试支付，测试支付产生的消费流水，将不参与最终的分成结算：
     0：正常支付
     1：测试支付(是) 
     */
    public String IsTest;
    /**
     * 支付渠道名称。
     与华为的结算流程中，收入将按支付渠道汇总，并在结算单中体现。如果需要对账，
     开发者应当根据此字段汇总各个支付渠道的收入，并按支付渠道与华为对账。(是)
     */
    public String PayChannel;
    /**
     * 支付时应用传入SDK的透传信息。
     * 以上参数的MD5值，其中AppKey为游戏平台分配的应用密钥。
     String.Format("{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{
     11}{12}{13}{14}{15:yyyy-MM-dd HH:mm:ss}{16}{17}",
     Act,AppId,ThirdAppId,Uin,ConsumeStreamId,TradeNo,Subject,Amount,C
     hargeAmount,ChargeAmountIncVAT,ChargeAmountExclVAT,Country
     ,Currency,Share,Note,TradeStatus,CreateTime,IsTest, PayChannel, AppKey).HashToMD5Hex()
     HashToMD5Hex()由CP自己定义计算MD5的算法。
     为MD5加密算法的参考样例。(是)
     */
    public String Sign;


}