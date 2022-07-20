package com.base.basic.api.controller.v1;

import com.base.basic.app.service.OldGoodsService;
import com.base.basic.domain.entity.v1.OldGoods;
import com.base.basic.domain.repository.OldGoodsRepository;
import com.base.common.util.jwt.annotation.Access;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 二手商品表 管理 API
 */
@Api(tags="二手商品表")
@RestController
@RequestMapping("/old-goods")
public class OldGoodsController {

    @Autowired
    private OldGoodsRepository oldGoodsRepository;
    @Autowired
    private OldGoodsService oldGoodsService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "商品列表")
    @GetMapping("")
    public LayJson<OldGoods> pageList(PageParmaters pageParmaters, OldGoods searchBody, @RequestParam(value = "param", required = false) String param) {
        return new LayJson<>(oldGoodsService.pageList(pageParmaters, searchBody));
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "商品明细")
    @GetMapping("/detail/{id}")
    public OldGoods detail(@ApiParam(value = "id", required = true) @PathVariable("id") Long id) {
        return oldGoodsService.detail(id);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "商品创建/更新")
    @PostMapping("/save")
    public OldGoods save(@RequestBody OldGoods oldGoods) {
        return oldGoodsService.save(oldGoods);
    }

    @ApiOperation(value = "商品导出")
    @GetMapping("/export")
    @Access(accessNoToken = true)
    public String exportData(HttpServletResponse response){
        return oldGoodsService.exportData(response);
    }

    @ApiOperation(value = "商品导入")
    @PostMapping("/import")
    @Access(accessNoToken = true)
    public String importData(@ApiParam(value="选择文件",required=true) @RequestPart("fileName") MultipartFile file){
        return oldGoodsService.importData(file);
    }
}
