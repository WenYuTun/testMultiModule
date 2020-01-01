package com.supermap.testMultiModule.dao.miaosha;

import com.supermap.testMultiModule.pojo.miaosha.SecOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface SecOrderMapper {
    /**
     * 删除订单
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新增订单
     * @param record
     * @return
     */
    int insert(SecOrder record);

    /**
     * 通过ID查询订单
     * @param id
     * @return
     */
    SecOrder selectByPrimaryKey(String id);

    /**
     * 查询全部订单
     * @return
     */
    List<SecOrder> selectAll();

    /**
     * 全量更新订单
     * @param record
     * @return
     */
    int updateByPrimaryKey(SecOrder record);

    /**
     * 增量更新
     * @param record
     * @return
     */
    int updateByKey(SecOrder record);

    /**
     * 通过商品ID查询订单
     * @param productId
     * @return
     */
    List<SecOrder> selectByProductId(String productId);
}