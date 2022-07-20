package com.base.basic.domain.exc;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserExcelModel {

    @ExcelProperty(value = "用户名" ,index = 0)
    private String loginName;

    @ExcelProperty(value = "邮箱" ,index = 1)
    private String email;

    @ExcelProperty(value = "创建时间" ,index = 2)
    private Date creationDate;
}
