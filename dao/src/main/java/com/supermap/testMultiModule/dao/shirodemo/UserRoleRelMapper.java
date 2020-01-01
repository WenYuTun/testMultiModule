package com.supermap.testMultiModule.dao.shirodemo;

import com.supermap.testMultiModule.pojo.shirodemo.UserRoleRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserRoleRelMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRel record);

    UserRoleRel selectByPrimaryKey(Long id);

    List<UserRoleRel> selectAll();

    int updateByPrimaryKey(UserRoleRel record);
}