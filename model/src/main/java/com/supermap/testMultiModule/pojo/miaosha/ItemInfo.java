package com.supermap.testMultiModule.pojo.miaosha;

import java.math.BigDecimal;
import java.util.Date;

public class ItemInfo {
    /**
     * ����
     */
    private Integer id;

    /**
     * ��Ʒ����
     */
    private String code;

    /**
     * ����
     */
    private String name;

    /**
     * ���ۼ�
     */
    private BigDecimal price;

    /**
     * �Ƿ���Ч��1=�ǣ�0=��
     */
    private Integer isActive;

    /**
     * ����ʱ��
     */
    private Date createTime;

    /**
     * ����ʱ��
     */
    private Date updateTime;

    /**
     * ����
     * @return id ����
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����
     * @param id ����
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��Ʒ����
     * @return code ��Ʒ����
     */
    public String getCode() {
        return code;
    }

    /**
     * ��Ʒ����
     * @param code ��Ʒ����
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ����
     * @return name ����
     */
    public String getName() {
        return name;
    }

    /**
     * ����
     * @param name ����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���ۼ�
     * @return price ���ۼ�
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * ���ۼ�
     * @param price ���ۼ�
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * �Ƿ���Ч��1=�ǣ�0=��
     * @return is_active �Ƿ���Ч��1=�ǣ�0=��
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * �Ƿ���Ч��1=�ǣ�0=��
     * @param isActive �Ƿ���Ч��1=�ǣ�0=��
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * ����ʱ��
     * @return create_time ����ʱ��
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * ����ʱ��
     * @param createTime ����ʱ��
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * ����ʱ��
     * @return update_time ����ʱ��
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * ����ʱ��
     * @param updateTime ����ʱ��
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}