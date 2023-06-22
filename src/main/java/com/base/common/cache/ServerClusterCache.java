package com.base.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerClusterCache {

    /**
     * 集群地址信息
     * key IP+端口
     * value 状态 UP/DOWN
     */
    private static Map<String, String> serverCluster = new ConcurrentHashMap<>();

    public static Map<String, String> getServerCluster() {
        return serverCluster;
    }

    public static void setServerCluster(Map<String, String> serverCluster) {
        ServerClusterCache.serverCluster = serverCluster;
    }

    /**
     * 标记服务上线
     * @param server
     */
    public static void serverUp(String server) {
        ServerClusterCache.serverCluster.put(server, "UP");
    }

    /**
     * 标记服务下线
     * @param server
     */
    public static void serverDown(String server) {
        ServerClusterCache.serverCluster.put(server, "DOWN");
    }
}
