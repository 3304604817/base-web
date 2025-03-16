package com.base.basic;

import com.base.common.util.local.LocalAddressUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import static com.base.common.util.local.LocalAddressUtils.getIpv6;

@RunWith(SpringRunner.class)
public class BasicTest {

    @Test
    public void test() throws UnknownHostException, SocketException {
        System.out.println("--------------------------------------------------");

        for(String aaa:LocalAddressUtils.getIpv6()){
            System.out.println(aaa);
        }

        // 获取所有的网络接口
//        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//
//        while (networkInterfaces.hasMoreElements()) {
//            NetworkInterface networkInterface = networkInterfaces.nextElement();
//
//            // 获取每个网络接口的IP地址
//            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
//
//            while (inetAddresses.hasMoreElements()) {
//                InetAddress inetAddress = inetAddresses.nextElement();
//
//                // 检查是否是IPv6地址
//                if (inetAddress instanceof Inet6Address) {
//                    System.out.println("Interface: " + networkInterface.getName());
//                    System.out.println("IPv6 Address: " + inetAddress.getHostAddress());
//                }
//            }
//        }


        System.out.println("--------------------------------------------------");
    }
}
