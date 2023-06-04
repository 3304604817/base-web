package com.base.basic.api.controller.v0;

import com.github.pagehelper.PageInfo;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.app.service.UserService;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags="用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * @CrossOrigin 让接口支持跨域请求
     */
    @Autowired
    private UserService userService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "获取当前登陆用户信息")
    @GetMapping("/current-user")
    @CrossOrigin
    public ResponseEntity currentUser() {
        return new ResponseEntity(userService.currentUser(), HttpStatus.OK);
    }

    @ApiOperation(value = "员工列表")
    @GetMapping("/list")
    @CrossOrigin
    public ResponseEntity list(@RequestParam(defaultValue = "1", required = false) int page,
                               @RequestParam(defaultValue = "10", required = false) int size,
                               @RequestParam(value = "loginName", required = false) String loginName,
                               @RequestParam(value = "realName", required = false) String realName) {
        IamUser iamUser = new IamUser();
        iamUser.setLoginName(loginName);
        iamUser.setRealName(realName);
        PageInfo<IamUser> pageInfo = userService.list(iamUser, page, size);
        return new ResponseEntity(pageInfo, HttpStatus.OK);
    }

    @ApiOperation(value = "员工明细")
    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity detail(@ApiParam(required = true, value = "员工ID") @PathVariable("userId") Long userId) {
        IamUser iamUser = userService.detail(userId);
        return new ResponseEntity(iamUser, HttpStatus.OK);
    }

    @ApiOperation(value = "创建员工")
    @PostMapping("/insert")
    @CrossOrigin
    public ResponseEntity insert(@RequestBody IamUser iamUser) {
        iamUser.setLoginName(null == iamUser.getLoginName() ? UUID.randomUUID().toString() : iamUser.getLoginName());
        iamUser = userService.insert(iamUser);
        return new ResponseEntity(iamUser, HttpStatus.OK);
    }

    @ApiOperation(value = "批量创建员工")
    @PostMapping("/batchInsert")
    public ResponseEntity batchInsert(@RequestBody List<IamUser> iamUsers) {
        iamUsers = userService.batchInsert(iamUsers);
        return new ResponseEntity(iamUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "更新员工信息")
    @PutMapping("/update")
    public ResponseEntity<IamUser> update(@RequestBody IamUser iamUser) {
        iamUser = userService.update(iamUser);
        return new ResponseEntity(iamUser, HttpStatus.OK);
    }

    @ApiOperation(value = "批量删除员工")
    @DeleteMapping("/batchDelete")
    @CrossOrigin
    public ResponseEntity batchDelete(@RequestBody List<IamUser> iamUsers) {
        userService.batchDelete(iamUsers);
        return new ResponseEntity(HttpStatus.OK);
    }
}
