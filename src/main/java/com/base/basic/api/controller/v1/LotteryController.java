package com.base.basic.api.controller.v1;

import com.base.basic.app.service.LotteryDltHistoryService;
import com.base.common.util.jwt.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.gao
 * @description
 * @date 2022/9/13 11:13
 */
@Api(tags="彩票管理")
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryDltHistoryService lotteryDltHistoryService;

    @ApiOperation(value = "大乐透历史中奖信息抓取")
    @PostMapping("/dlt/getHistory")
    @Access(accessNoToken = true)
    public ResponseEntity getDltHistory() {
        lotteryDltHistoryService.getDltHistory();
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "大乐透历史中奖信息抓取")
    @PostMapping("/dlt/dataAnalysis")
    @Access(accessNoToken = true)
    public ResponseEntity dataAnalysis() {
        lotteryDltHistoryService.dltDataAnalysis();
        return new ResponseEntity(HttpStatus.OK);
    }
}
