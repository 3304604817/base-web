package com.base.basic.app.service.impl;

import com.base.basic.app.service.ServerClusterService;
import com.base.basic.domain.entity.v1.ServerCluster;
import com.base.basic.infra.mapper.ServerClusterMapper;
import com.base.common.cache.ServerClusterCache;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServerClusterServiceImpl implements ServerClusterService {

    @Autowired
    @SuppressWarnings("all")
    public ServerClusterMapper serverClusterMapper;

    @Override
    public Boolean refresh() {
        /**
         * 把数据库配置集群信息刷新到内存中
         */
        List<ServerCluster> clusters = serverClusterMapper.selectAll();
        Map<String, String> serverCluster = new ConcurrentHashMap<>();
        for(ServerCluster cluster:clusters){
            serverCluster.put(new StringBuilder(cluster.getIp()).append(":").append(cluster.getPort()).toString(), cluster.getStatus());
        }
        ServerClusterCache.setServerCluster(serverCluster);
        return Boolean.TRUE;
    }

    @Override
    public PageInfo<ServerCluster> clusterStatus(PageParmaters pageParmaters){
        PageInfo<ServerCluster> page = PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> serverClusterMapper.selectAll());
        for(ServerCluster serverCluster:page.getList()){
            serverCluster.setStatus(ServerClusterCache.getServerCluster().get(serverCluster.getIp() + ":" + serverCluster.getPort()));
        }
        return page;
    }
}
