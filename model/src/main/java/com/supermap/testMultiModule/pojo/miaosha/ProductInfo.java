package com.supermap.testMultiModule.pojo.miaosha;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo {

    /**
     * 主键
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 商品状态 0正常 1下架
     */
    private Integer productStatus;

    /**
     * 类目编号
     */
    private String categoryType;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public ProductInfo(String productId) {
        this.productId = productId;
        this.productPrice = new BigDecimal(3.2);
    }

    public ProductInfo() {

    }

    /**
     * 主键
     * @return product_id 主键
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 主键
     * @param productId 主键
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 商品名称
     * @return product_name 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 商品名称
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 单价
     * @return product_price 单价
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 单价
     * @param productPrice 单价
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 库存
     * @return product_stock 库存
     */
    public Integer getProductStock() {
        return productStock;
    }

    /**
     * 库存
     * @param productStock 库存
     */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
     * 商品描述
     * @return product_description 商品描述
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * 商品描述
     * @param productDescription 商品描述
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * 小图
     * @return product_icon 小图
     */
    public String getProductIcon() {
        return productIcon;
    }

    /**
     * 小图
     * @param productIcon 小图
     */
    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    /**
     * 商品状态 0正常 1下架
     * @return product_status 商品状态 0正常 1下架
     */
    public Integer getProductStatus() {
        return productStatus;
    }

    /**
     * 商品状态 0正常 1下架
     * @param productStatus 商品状态 0正常 1下架
     */
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 类目编号
     * @return category_type 类目编号
     */
    public String getCategoryType() {
        return categoryType;
    }

    /**
     * 类目编号
     * @param categoryType 类目编号
     */
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * 创建日期
     * @return create_time 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}