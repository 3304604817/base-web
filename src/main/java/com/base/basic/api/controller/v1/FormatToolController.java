package com.base.basic.api.controller.v1;

import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.FormatToolService;
import com.base.basic.domain.entity.v1.OldGoods;
import com.base.basic.domain.vo.v0.FormatToolVO;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Api(tags="格式化工具")
@RestController
@RequestMapping("/format-tool")
public class FormatToolController {

    @Autowired
    private FormatToolService formatToolService;

    @ApiOperation(value = "Json格式化")
    @PostMapping("/json")
    @Access(accessNoToken = true)
    public FormatToolVO jsonFormat(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.jsonFormat(formatToolVO);
    }

    @ApiOperation(value = "Base64加密")
    @PostMapping("/base64-encoder")
    @Access(accessNoToken = true)
    public FormatToolVO base64Encryp(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.base64Encryp(formatToolVO);
    }

    @ApiOperation(value = "Base64解密")
    @PostMapping("/base64-decrypt")
    @Access(accessNoToken = true)
    public FormatToolVO base64Decrypt(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.base64Decrypt(formatToolVO);
    }

    @ApiOperation(value = "MD5加密")
    @PostMapping("/md5-encoder")
    @Access(accessNoToken = true)
    public FormatToolVO md5Encryp(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.md5Encryp(formatToolVO);
    }

    @ApiOperation(value = "MD5解密")
    @PostMapping("/md5-decrypt")
    @Access(accessNoToken = true)
    public FormatToolVO md5Decrypt(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.md5Decrypt(formatToolVO);
    }

    @ApiOperation(value = "UUID生成")
    @PostMapping("/uuid-generate")
    @Access(accessNoToken = true)
    public FormatToolVO uuidGenerate(@RequestBody FormatToolVO formatToolVO) {
        return formatToolService.uuidGenerate(formatToolVO);
    }

    @ApiOperation(value = "URL编码")
    @PostMapping("/url-encoder")
    @Access(accessNoToken = true)
    public FormatToolVO urlEncoder(@RequestBody FormatToolVO formatToolVO) throws UnsupportedEncodingException {
        return formatToolService.urlEncoder(formatToolVO);
    }

    @ApiOperation(value = "URL解码")
    @PostMapping("/url-decoder")
    @Access(accessNoToken = true)
    public FormatToolVO urlDecoder(@RequestBody FormatToolVO formatToolVO) throws UnsupportedEncodingException {
        return formatToolService.urlDecoder(formatToolVO);
    }
}
