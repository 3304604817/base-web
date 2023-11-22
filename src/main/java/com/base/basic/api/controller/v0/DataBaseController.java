package com.base.basic.api.controller.v0;

import com.base.basic.app.service.DataBaseService;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="数据库管理")
@RestController
@RequestMapping("/db")
public class DataBaseController {

    @Autowired
    private DataBaseService dataBaseService;

    @ApiOperation(value = "查询表名")
    @GetMapping("/table/page")
    public ResponseEntity<LayJson<TableVO>> list(PageParmaters pageParmaters, TableVO tableVO) {
        return new ResponseEntity(new LayJson<>(dataBaseService.pageList(pageParmaters, tableVO)), HttpStatus.OK);
    }
}
