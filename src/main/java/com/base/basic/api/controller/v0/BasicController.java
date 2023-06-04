package com.base.basic.api.controller.v0;

import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="基础")
@RestController
@RequestMapping("")
public class BasicController {

    Logger logger = LoggerFactory.getLogger(BasicController.class);

    @ApiOperation(value = "测试")
    @PostMapping("/test")
    @Access(accessNoToken = true)
    public ResponseEntity loginOut() {
        try {
            String str = null;
            String str1 = "";
            if(str.equals(str1)){
                System.out.println(1);
            }
        }catch (Exception e){
            logger.error("e:{}", e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
