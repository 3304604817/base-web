package com.base.basic.api.controller.v0;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import com.base.common.annotation.Access;
import com.base.common.current.CurrentUserHelper;
import com.base.common.exception.BaseException;
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
    public ResponseEntity loginOut() {
        CurrentUserVO user = CurrentUserHelper.userDetail();
        return new ResponseEntity(HttpStatus.OK);
    }
}
