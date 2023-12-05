package com.base.basic.api.controller.v0;

import com.base.basic.app.service.BaseRobotService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="机器人")
@RestController
@RequestMapping("base-robot")
public class BaseRobotController {

    @Autowired
    private BaseRobotService baseRobotService;
}
