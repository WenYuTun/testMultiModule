package com.supermap.testMultiModule.dao.shirodemo;

import com.supermap.testMultiModule.pojo.shirodemo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}