package com.base.basic.api.controller.v1;

import com.base.basic.app.service.DataDictionaryService;
import com.base.basic.domain.entity.v1.DataDictionary;
import com.base.basic.domain.repository.DataDictionaryRepository;
import com.base.common.util.jwt.annotation.Access;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典
 */
@Api(tags="数据字典")
@RestController
@RequestMapping("/data-dictionary")
public class DataDictionaryController {

    @Autowired
    private DataDictionaryRepository dataDictionarysRepository;
    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Access(accessNoToken = true)
    @ApiOperation(value = "数据字典列表")
    @GetMapping("")
    public LayJson<DataDictionary> pageList(PageParmaters pageParmaters, DataDictionary dataDictionary) {
        return new LayJson<>(dataDictionaryService.pageList(pageParmaters, dataDictionary));
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "数据字典保存/更新")
    @PostMapping("/save")
    public DataDictionary save(@RequestBody DataDictionary dataDictionary) {
        return dataDictionaryService.save(dataDictionary);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "数据字典明细")
    @GetMapping("/detail/{id}")
    public DataDictionary detail(@ApiParam(value = "id", required = true) @PathVariable("id") Long id) {
        return dataDictionaryService.detail(id);
    }

    @Access(accessNoToken = true)
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id") Long id) {
        return dataDictionaryService.delete(id);
    }
}
