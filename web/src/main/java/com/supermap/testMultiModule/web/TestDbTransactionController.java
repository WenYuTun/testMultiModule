package com.supermap.testMultiModule.web;

import com.github.pagehelper.PageInfo;
import com.supermap.testMultiModule.ov.ValidationResult;
import com.supermap.testMultiModule.component.ValidatorComponent;
import com.supermap.testMultiModule.config.WebLog;
import com.supermap.testMultiModule.ov.miaosha.AddItemInfoReq;
import com.supermap.testMultiModule.service.ItemInfoService;
import com.supermap.testMultiModule.pojo.miaosha.ItemInfo;
import com.supermap.testMultiModule.ov.ResponseJsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wenyutun
 * @description: item和user接口
 * @date: 2019/6/17
 * @version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestDbTransactionController {

    @Autowired
    private ItemInfoService itemService;

    /**
     * 参数校验组件
     */
    @Autowired
    private ValidatorComponent validator;

    /**
     * 获取全部商品详情接口
     *
     * @return
     */
    @GetMapping("/item/getAll")
    @WebLog(description = "获取全部item")//使用自定义注解，打印出该类的请求参数
    public ResponseJsonData getItemInfoAll() {
        final List<ItemInfo> itemInfos = itemService.selectItemInfo();
        if (itemInfos != null && itemInfos.size() > 0) {
            return ResponseJsonData.ok(itemInfos);
        }
        return ResponseJsonData.errorException("未查询到相关记录");
    }

    /**
     * 分页获取商品详情接口
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/item/getByPage")
    public ResponseJsonData getItemInfoAll(@RequestParam(value = "pageIndex") Integer pageIndex,
                                           @RequestParam(value = "pageSize") Integer pageSize) {
        final PageInfo pageInfo = itemService.selectItemInfoByPage(pageIndex, pageSize);

        if (pageInfo.getList() != null && pageInfo.getList().size() > 0) {
            return ResponseJsonData.ok(pageInfo);
        }
        return ResponseJsonData.errorException("未查询到相关记录");
    }


}
