package com.supermap.testMultiModule.dao.miaosha;

import com.supermap.testMultiModule.pojo.miaosha.ItemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ItemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemInfo record);

    ItemInfo selectByPrimaryKey(Integer id);

    List<ItemInfo> selectAll();

    int updateByPrimaryKey(ItemInfo record);
}