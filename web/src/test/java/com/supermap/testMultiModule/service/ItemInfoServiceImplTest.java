package com.supermap.testMultiModule.service;

import com.supermap.testMultiModule.pojo.miaosha.ItemInfo;
import com.supermap.testMultiModule.service.ItemInfoService;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemInfoServiceImplTest extends TestCase {

    @Autowired
    private ItemInfoService itemInfoService;

    public void testBatchInsert() {
        List<ItemInfo> itemInfos = new ArrayList<>();
        ItemInfo itemInfo = new ItemInfo();
        itemInfoService.batchInsert(itemInfos);
    }
}