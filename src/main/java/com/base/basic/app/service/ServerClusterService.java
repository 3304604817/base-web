package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.ServerCluster;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface ServerClusterService {

    /**
     * 刷新服务器集群缓存
     * @return
     */
    Boolean refresh();

    /**
     * 查集群状态
     * @return
     */
    PageInfo<ServerCluster> clusterStatus(PageParmaters pageParmaters);
}
