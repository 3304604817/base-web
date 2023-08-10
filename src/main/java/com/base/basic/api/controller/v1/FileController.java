package com.base.basic.api.controller.v1;

import com.base.basic.app.service.FileService;
import com.base.basic.domain.entity.v1.OldGoods;
import com.base.common.annotation.Access;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Api(tags="文件管理")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "根据硬盘地址下载")
    @GetMapping("/download/disk-path")
    @Access(accessNoToken = true)
    public void downloadDiskPath(HttpServletResponse response,
                                 @RequestParam(value = "path", required = true) String path) {
        fileService.downloadDiskPath(response, path);
    }

    @ApiOperation(value = "根据网络地址下载")
    @GetMapping("/download/network")
    @Access(accessNoToken = true)
    public void downloadNetwork(HttpServletResponse response,
                                @RequestParam(value = "netAddress", required = true) String netAddress,
                                @RequestParam(value = "filename", required = false) String filename) {
        fileService.downloadNetwork(response, netAddress, filename);
    }
}
