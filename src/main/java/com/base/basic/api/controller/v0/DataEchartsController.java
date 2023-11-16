package com.base.basic.api.controller.v0;

import com.base.basic.app.service.DataEchartsService;
import com.base.basic.domain.entity.v1.DataEcharts;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="数据可视化")
@RestController
@RequestMapping("/data-echarts")
public class DataEchartsController {
    @Autowired
    private DataEchartsService dataEchartsService;

    @ApiOperation(value = "查所有可视化数据")
    @GetMapping("/page")
    public ResponseEntity<LayJson<DataEcharts>> pageList(PageParmaters pageParmaters, DataEcharts searchBody) {
        return new ResponseEntity(new LayJson<>(dataEchartsService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }
}
