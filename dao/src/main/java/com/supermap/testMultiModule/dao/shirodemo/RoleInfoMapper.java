package com.supermap.testMultiModule.dao.shirodemo;

import com.supermap.testMultiModule.pojo.shirodemo.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RoleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleInfo record);

    RoleInfo selectByPrimaryKey(Long id);

    List<RoleInfo> selectAll();

    int updateByPrimaryKey(RoleInfo record);
}