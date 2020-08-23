package com.supermap.testMultiModule.service;

import com.github.pagehelper.PageInfo;
import com.supermap.testMultiModule.ov.miaosha.AddItemInfoReq;
import com.supermap.testMultiModule.pojo.miaosha.ItemInfo;


import java.util.List;

/**
 * @author wenyutun
 * @description: item和user接口
 * @date: 2019/6/17
 * @version: 1.0
 */
public interface ItemInfoService {

    /**
     * 查询商品信息
     * @return
     */
    List<ItemInfo> selectItemInfo();


    /**
     * 新增商品信息
     * @param addItemInfoReq
     * @return 新增数量
     */
    Integer insertItem(AddItemInfoReq addItemInfoReq);

    /**
     * 分页查询商品信息
     * @param pageIndex
     * @param pageSize
     * @return PageInfo 商品分页信息
     */
    PageInfo selectItemInfoByPage(Integer pageIndex, Integer pageSize);

    /**
     * 批量插入
     * @return
     */
    Integer batchInsert(List<ItemInfo> itemInfos);


}

