package com.base.basic.api.controller.v1;

import com.base.basic.domain.repository.OldGoodsDetailRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 二手商品详情 管理 API
 */
@Api(tags="二手商品表minxi")
@RestController
@RequestMapping("/old-goods-detail")
public class OldGoodsDetailController {

    @Autowired
    private OldGoodsDetailRepository oldGoodsDetailRepository;
}
