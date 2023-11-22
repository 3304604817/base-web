package com.base.basic.api.controller.v0;

import com.base.basic.app.service.DataBaseService;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.domain.vo.v0.TableVO;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "根据库名表名查列信息")
    @GetMapping("/table/columns")
    public ResponseEntity<List<ColumnVO>> columnList(@ApiParam(value="库名", required = true) @RequestParam("tableSchema") String tableSchema,
                                                     @ApiParam(value="表名", required = true) @RequestParam("tableName") String tableName) {
        return new ResponseEntity(dataBaseService.columnList(tableSchema, tableName), HttpStatus.OK);
    }

    @ApiOperation(value = "指定表名和查询条件查询表数据")
    @GetMapping("/table/data")
    public ResponseEntity<List<Map<String,Object>>> tableData(@ApiParam(value="库名", required = true) @RequestParam("tableSchema") String tableSchema,
                                                              @ApiParam(value="表名", required = true) @RequestParam("tableName") String tableName,
                                                              @ApiParam(value="表名", required = false) @RequestParam("whereSql") String whereSql) {
        return new ResponseEntity(dataBaseService.tableData(tableSchema, tableName, whereSql), HttpStatus.OK);
    }
}
