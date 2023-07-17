package com.base.basic.api.controller.v1;

import com.base.basic.app.service.RoleService;
import com.base.basic.domain.entity.v1.Role;
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

@Api(tags="角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查所有角色")
    @GetMapping("/page")
    public ResponseEntity<LayJson<Role>> pageList(PageParmaters pageParmaters, Role searchBody) {
        return new ResponseEntity(new LayJson<>(roleService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }
}
