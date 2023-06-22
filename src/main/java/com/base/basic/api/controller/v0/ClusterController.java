package com.base.basic.api.controller.v0;

import com.base.basic.app.service.ServerClusterService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.ServerCluster;
import com.base.common.annotation.Access;
import com.base.common.cache.ServerClusterCache;
import com.base.common.util.layui.LayJson;
import com.base.common.util.page.PageParmaters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "查所有集群状态")
    @GetMapping("/status")
    public ResponseEntity<LayJson<ServerCluster>> clusterStatus(PageParmaters pageParmaters) {
        return new ResponseEntity(new LayJson<>(serverClusterService.clusterStatus(pageParmaters)), HttpStatus.OK);
    }
}
