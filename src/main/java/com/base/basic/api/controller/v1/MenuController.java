package com.base.basic.api.controller.v1;

import com.base.basic.app.service.MenuService;
import com.base.basic.domain.entity.v1.Menu;
import com.base.basic.domain.entity.v1.Role;
import com.base.basic.domain.vo.v0.MenuVO;
import com.base.basic.infra.mapper.MenuMapper;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags="菜单维护")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "主页初始化加载菜单")
    @GetMapping("/init")
    public ResponseEntity<MenuVO> initMenu(PageParmaters pageParmaters, Menu searchBody) {
        return new ResponseEntity(menuService.initMenu(), HttpStatus.OK);
    }

    @ApiOperation(value = "查所有菜单")
    @GetMapping("/page")
    public ResponseEntity<LayJson<Menu>> pageList(PageParmaters pageParmaters, Menu searchBody) {
        return new ResponseEntity(new LayJson<>(menuService.pageList(pageParmaters, searchBody)), HttpStatus.OK);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("/add")
    public ResponseEntity<Menu> add(@Validated @RequestBody Menu role) {
        return new ResponseEntity(menuService.add(role), HttpStatus.OK);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/update")
    public ResponseEntity<Menu> update(@RequestBody Menu role) {
        return new ResponseEntity(menuService.update(role), HttpStatus.OK);
    }
}
