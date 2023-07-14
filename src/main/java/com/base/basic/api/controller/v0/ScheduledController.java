package com.base.basic.api.controller.v0;

import com.base.basic.app.service.ScheduledService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="定时任务")
@RestController
@RequestMapping("/scheduled")
public class ScheduledController {

    @Autowired
    private ScheduledService scheduledService;

    @ApiOperation(value = "查所有定时任务")
    @GetMapping("/page")
    public ResponseEntity<LayJson<Scheduled>> pageList(PageParmaters pageParmaters, Scheduled searchBody) {
        return new ResponseEntity(new LayJson<>(scheduledService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }

    @ApiOperation(value = "新增定时任务")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody Scheduled scheduled) {
        scheduledService.add(scheduled);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "修改定时任务")
    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Scheduled scheduled) {
        scheduledService.edit(scheduled);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "删除定时任务")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Scheduled scheduled) {
        scheduledService.delete(scheduled);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "暂停定时任务")
    @PostMapping("/pause")
    public ResponseEntity pause(@RequestBody Scheduled scheduled) {
        scheduledService.pause(scheduled);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "启用定时任务")
    @PostMapping("/enable")
    public ResponseEntity enable(@RequestBody Scheduled scheduled) {
        scheduledService.enable(scheduled);
        return new ResponseEntity(HttpStatus.OK);
    }
}
