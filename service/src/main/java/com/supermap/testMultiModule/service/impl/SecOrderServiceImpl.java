package com.supermap.testMultiModule.service.impl;

import com.supermap.testMultiModule.dao.miaosha.SecOrderMapper;
import com.supermap.testMultiModule.pojo.miaosha.SecOrder;
import com.supermap.testMultiModule.service.SecOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecOrderServiceImpl implements SecOrderService {

    @Autowired
    private SecOrderMapper secOrderMapper;


    @Override
    public List<SecOrder> findByProductId(String productId) {
        List<SecOrder> secOrders = secOrderMapper.selectByProductId(productId);
        return secOrders;
    }

    @Override
    public SecOrder save(SecOrder secOrder) {
        final int insert = secOrderMapper.insert(secOrder);
        if (insert > 0) {
            return secOrder;
        }
        return null;
    }
}
