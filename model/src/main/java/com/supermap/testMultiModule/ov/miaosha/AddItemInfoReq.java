package com.supermap.testMultiModule.ov.miaosha;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AddItemInfoReq {

    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "code不能为空")
    private String code;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "price不能为空")
    private BigDecimal price;


    private Integer isActive;


    private Date createTime;


    private Date updateTime;

}
