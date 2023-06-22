package com.base.basic.api.controller.v0;

import com.base.basic.app.service.DbCacheService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="刷新缓存")
@RestController
@RequestMapping("/refresh-cache")
public class RefreshCacheController {

    @Autowired
    private DbCacheService dbCacheService;

    @ApiOperation(value = "查所有缓存")
    @GetMapping("/page")
    public ResponseEntity<LayJson<DbCache>> pageList(PageParmaters pageParmaters, DbCache searchBody) {
        return new ResponseEntity(new LayJson<>(dbCacheService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }

    @ApiOperation(value = "更新缓存")
    @PutMapping("/update")
    public ResponseEntity<DbCache> pageList(@RequestBody DbCache dbCache) {
        return new ResponseEntity(dbCacheService.update(dbCache), HttpStatus.OK);
    }

    @ApiOperation(value = "删除缓存")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody DbCache dbCache) {
        dbCacheService.delete(dbCache);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "刷新所有缓存")
    @PostMapping("/all")
    public ResponseEntity all() {
        dbCacheService.all();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "刷新数据库缓存")
    @PostMapping("/db-prefix")
    public ResponseEntity dbPrefix() {
        dbCacheService.dbPrefix();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "刷新配置缓存")
    @PostMapping("/config")
    public ResponseEntity config() {
        dbCacheService.config();
        return new ResponseEntity(HttpStatus.OK);
    }
}
