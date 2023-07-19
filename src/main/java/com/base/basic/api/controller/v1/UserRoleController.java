package com.base.basic.api.controller.v1;

import com.base.basic.app.service.UserRoleService;
import com.base.basic.domain.entity.v1.UserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="用户角色关系管理")
@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "查询用户角色关系列表")
    @GetMapping("/list")
    public ResponseEntity<List<UserRole>> list(UserRole userRole) {
        return new ResponseEntity(userRoleService.list(userRole), HttpStatus.OK);
    }

    @ApiOperation(value = "保存用户角色关系")
    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody UserRole userRole) {
        return new ResponseEntity(userRoleService.save(userRole), HttpStatus.OK);
    }
}
