package com.base.basic.api.controller.v1;

import com.base.basic.app.service.RoleService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "新增角色")
    @PostMapping("/add")
    public ResponseEntity add(@Validated @RequestBody Role role) {
        return new ResponseEntity(roleService.add(role), HttpStatus.OK);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody Role role) {
        return new ResponseEntity(roleService.edit(role), HttpStatus.OK);
    }

    @ApiOperation(value = "启用")
    @PostMapping("/enable")
    public ResponseEntity<Boolean> enable(@RequestBody Role role) {
        return new ResponseEntity(roleService.enable(role.getRoleId()), HttpStatus.OK);
    }

    @ApiOperation(value = "禁用")
    @PostMapping("/disabled")
    public ResponseEntity<Boolean> disabled(@RequestBody Role role) {
        return new ResponseEntity(roleService.disabled(role.getRoleId()), HttpStatus.OK);
    }

    @ApiOperation(value = "保存角色菜单关系")
    @PostMapping("/menu/save")
    public ResponseEntity roleMenuSave(@RequestBody Role role) {
        return new ResponseEntity(roleService.roleMenuSave(role), HttpStatus.OK);
    }
}
