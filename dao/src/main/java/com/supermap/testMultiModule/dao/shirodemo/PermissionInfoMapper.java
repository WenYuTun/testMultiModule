package com.supermap.testMultiModule.dao.shirodemo;

import com.supermap.testMultiModule.pojo.shirodemo.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(Long id);

    List<PermissionInfo> selectAll();

    int updateByPrimaryKey(PermissionInfo record);
}