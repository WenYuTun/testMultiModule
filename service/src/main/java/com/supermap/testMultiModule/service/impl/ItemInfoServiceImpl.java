package com.supermap.testMultiModule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermap.testMultiModule.ov.miaosha.AddItemInfoReq;
import com.supermap.testMultiModule.service.ItemInfoService;
import com.supermap.testMultiModule.dao.miaosha.ItemInfoMapper;
import com.supermap.testMultiModule.pojo.miaosha.ItemInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
/**
 * @author wenyutun
 * @description: item商品和用户user服务接口实现类
 * @date: 2019/6/17
 * @version: 1.0
 */
@Service
public class ItemInfoServiceImpl implements ItemInfoService {

    @Autowired
    private ItemInfoMapper itemInfoMapper;


    @Override
    public List<ItemInfo> selectItemInfo() {
        return itemInfoMapper.selectAll();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer insertItem(AddItemInfoReq addItemInfoReq) {
        ItemInfo itemInfo = new ItemInfo();
        BeanUtils.copyProperties(addItemInfoReq, itemInfo);
        itemInfo.setCreateTime(new Date());
        final int insert = itemInfoMapper.insert(itemInfo);
        //测试设置手动回滚返回参数
        try {
            if(true) {
                throw new RuntimeException("此处抛出异常");
            }
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return insert;
    }

    @Override
    public PageInfo selectItemInfoByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        final List<ItemInfo> itemInfos = itemInfoMapper.selectAll();
        return new PageInfo(itemInfos);
    }



}
