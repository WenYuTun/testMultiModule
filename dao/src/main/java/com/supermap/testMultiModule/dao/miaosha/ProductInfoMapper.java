package com.supermap.testMultiModule.dao.miaosha;

import com.supermap.testMultiModule.pojo.miaosha.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ProductInfoMapper {
    /**
     * 删除
     * @param productId
     * @return
     */
    int deleteByPrimaryKey(String productId);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(ProductInfo record);

    /**
     * 通过ID查询商品
     * @param productId
     * @return
     */
    ProductInfo selectByPrimaryKey(String productId);

    /**
     * 查询全部商品
     * @return
     */
    List<ProductInfo> selectAll();

    /**
     * 全量跟新
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductInfo record);

    /**
     * 增量跟新
     * @param record
     * @return
     */
    int updateByKey(ProductInfo record);
}