package com.base.basic.api.controller.v0;

import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.Role;
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
}
