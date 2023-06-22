package com.base.basic.api.controller.v0;

import com.base.basic.app.service.ServerClusterService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v0.base.BaseResponseEntity;
import com.base.common.annotation.Access;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static com.base.basic.domain.entity.v0.base.BaseResponseEntity.*;
import static com.base.basic.domain.entity.v0.base.BaseResponseEntity.STATUS_FAIL;

@Api(tags="集群管理")
@RestController
@RequestMapping("/cluster")
public class ClusterController {

    @Autowired
    private ServerClusterService serverClusterService;

    @ApiOperation(value = "心跳检查")
    @GetMapping("/heart-check")
    @Access(accessNoToken = true)
    public Boolean heartCheck(){
        return Boolean.TRUE;
    }

    @ApiOperation(value = "刷新服务器缓存")
    @PostMapping("/refresh")
    public ResponseEntity<Boolean> refresh(){
        return new ResponseEntity<>(serverClusterService.refresh(), HttpStatus.OK);
    }
}
