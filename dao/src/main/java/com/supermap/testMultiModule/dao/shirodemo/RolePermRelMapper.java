package com.supermap.testMultiModule.dao.shirodemo;

import com.supermap.testMultiModule.pojo.shirodemo.RolePermRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RolePermRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermRel record);

    RolePermRel selectByPrimaryKey(Long id);

    List<RolePermRel> selectAll();

    int updateByPrimaryKey(RolePermRel record);
}