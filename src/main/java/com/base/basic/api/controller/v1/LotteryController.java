package com.base.basic.api.controller.v1;

import com.base.basic.app.service.LotteryDltHistoryService;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @ApiOperation(value = "大乐透历史数据分析-查各区数字分布情况")
    @PostMapping("/dlt/dataAnalysis0")
    public ResponseEntity dataAnalysis0(@RequestParam(value = "drawTimeFm", required = false) String drawTimeFm,
                                       @RequestParam(value = "drawTimeTo", required = false) String drawTimeTo) {
        lotteryDltHistoryService.dltDataAnalysis0(drawTimeFm, drawTimeTo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "大乐透历史数据分析-查前后区所有数字分布")
    @PostMapping("/dlt/dataAnalysi1")
    public ResponseEntity dltDataAnalysis1(@RequestParam(value = "drawTimeFm", required = false) String drawTimeFm,
                                       @RequestParam(value = "drawTimeTo", required = false) String drawTimeTo) {
        lotteryDltHistoryService.dltDataAnalysis1(drawTimeFm, drawTimeTo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "大乐透历史数据分析-查前后区数字和")
    @PostMapping("/dlt/dataAnalysi2")
    public ResponseEntity dltDataAnalysis2(@RequestParam(value = "drawTimeFm", required = false) String drawTimeFm,
                                           @RequestParam(value = "drawTimeTo", required = false) String drawTimeTo) {
        lotteryDltHistoryService.dltDataAnalysis2(drawTimeFm, drawTimeTo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "大乐透历史数据分析-所有中奖号码汇总")
    @PostMapping("/dlt/dataAnalysi3")
    public ResponseEntity dltDataAnalysis3(@RequestParam(value = "drawTimeFm", required = false) String drawTimeFm,
                                           @RequestParam(value = "drawTimeTo", required = false) String drawTimeTo) {
        lotteryDltHistoryService.dltDataAnalysis3(drawTimeFm, drawTimeTo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "大乐透历史数据分析-重复中奖号")
    @PostMapping("/dlt/dataAnalysi4")
    public ResponseEntity dltDataAnalysis4(@RequestParam(value = "drawTimeFm", required = false) String drawTimeFm,
                                           @RequestParam(value = "drawTimeTo", required = false) String drawTimeTo) {
        lotteryDltHistoryService.dltDataAnalysis4(drawTimeFm, drawTimeTo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
