package com.base.basic.api.controller.v0;

import com.alibaba.excel.EasyExcel;
import com.base.basic.app.service.EasyExcelService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.basic.domain.repository.UserRepository;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags="EasyExcel")
@RestController
@RequestMapping("/excel")
public class EasyExcelController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EasyExcelService easyExcelService;

    @ApiOperation(value = "导出")
    @GetMapping("/export")
    @Access(accessNoToken = true)
    public String exportData(HttpServletResponse response){
        return easyExcelService.exportData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("/import")
    @Access(accessNoToken = true)
    public String importData(@ApiParam(value="选择文件",required=true) @RequestPart("fileName") MultipartFile file){
        return easyExcelService.importData(file);
    }
}
