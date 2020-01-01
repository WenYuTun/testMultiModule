package com.supermap.testMultiModule.pojo.miaosha;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wenyutun
 * @description: ��Ʒ����ʵ����
 * @date: 2019/6/17
 * @version: 1.0
 */
@Data
public class SecOrder {
    /**
     * ����ID
     */
    private String id;

    /**
     * �û�ID
     */
    private String userId;

    /**
     * ��ƷID
     */
    private String productId;

    /**
     * ��Ʒ�۸�
     */
    private BigDecimal productPrice;

    /**
     * 
     */
    private BigDecimal amount;

}