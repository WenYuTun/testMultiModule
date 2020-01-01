package com.supermap.testMultiModule.pojo.shirodemo;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 用户信息表（user_info）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class UserInfo {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户姓名
     */
    private String userName;
}