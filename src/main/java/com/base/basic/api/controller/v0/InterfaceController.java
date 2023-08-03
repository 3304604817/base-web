package com.base.basic.api.controller.v0;

import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="接口管理")
@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Autowired
    private InterfaceService interfaceService;

    @ApiOperation(value = "查所有接口")
    @GetMapping("/page")
    public ResponseEntity<LayJson<Interface>> pageList(PageParmaters pageParmaters, Interface searchBody) {
        return new ResponseEntity(new LayJson<>(interfaceService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }

    @ApiOperation(value = "根据接口ID查接口")
    @GetMapping("/{interfaceId}")
    public ResponseEntity<Interface> interfaceDetail(@ApiParam(required = true, value = "接口ID") @PathVariable("interfaceId") Long interfaceId) {
        return new ResponseEntity(interfaceService.interfaceDetail(interfaceId), HttpStatus.OK);
    }

    @ApiOperation(value = "启用接口")
    @PostMapping("/enable")
    public ResponseEntity enable(@RequestBody Interface body) {
        return new ResponseEntity(interfaceService.enable(body.getId()), HttpStatus.OK);
    }

    @ApiOperation(value = "禁用接口")
    @PostMapping("/disabled")
    public ResponseEntity disabled(@RequestBody Interface body) {
        return new ResponseEntity(interfaceService.disabled(body.getId()), HttpStatus.OK);
    }
}
