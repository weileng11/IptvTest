package com.iptv.unicom.payContants;

/**
 * classes:com.iptv.com.iptv.unicompay.payContants.UnicomContants
 *
 * @author lt
 * @date 2016/6/16
 * @time 16:30
 * @description
 */
public class UnicomContants {
    
    //鉴权参数
    public static final String productId="txblyby025";
    public static final String productType="2";
    public static final String contentId="txblyby025";
    
    
    public static final int MPAYINIT_SUCCESS=0;
    public static final int MPAYINIT_FAIL=1;

    public static final int MPAYAUTO_SUCCESS=2;
    public static final int MPAYAUTO_FAIL=3;

    public static final int MPAYPURCHASE_SUCCESS=4;
    public static final int MPAYPURCHASE_FAIL=5;
    
    
    
    
    
    //鉴权失败为10020001 错误码，用来获取省份id和userid productId
    public static final String AUTHERROR_PRODUCTID="10020001";
}
