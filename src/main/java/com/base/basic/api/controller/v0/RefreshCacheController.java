package com.base.basic.api.controller.v0;

import com.base.basic.app.service.DbCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="刷新缓存")
@RestController
@RequestMapping("/refresh-cache")
public class RefreshCacheController {

    @Autowired
    private DbCacheService dbCacheService;

    @ApiOperation(value = "刷新数据库缓存")
    @PostMapping("/db-prefix")
    public ResponseEntity dbPrefix() {
        dbCacheService.dbPrefix();
        return new ResponseEntity(HttpStatus.OK);
    }
}
