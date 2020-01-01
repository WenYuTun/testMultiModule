package com.supermap.testMultiModule.pojo.shirodemo;

import lombok.Data;

/**
 * @author wenyutun
 * @description: 角色信息表（role_info）实体类模型
 * @date: 2019/8/10
 * @version: 1.0
 */
@Data
public class RoleInfo {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}