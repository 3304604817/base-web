package com.base.basic.domain.exc;

import com.alibaba.excel.annotation.ExcelProperty;
import java.util.Date;

public class UserExcelModel {

    @ExcelProperty(value = "用户名" ,index = 0)
    private String loginName;

    @ExcelProperty(value = "邮箱" ,index = 1)
    private String email;

    @ExcelProperty(value = "创建时间" ,index = 2)
    private Date creationDate;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
