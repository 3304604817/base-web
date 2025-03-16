package com.base.common.util.local;

import java.net.*;
import java.util.*;

public class LocalAddressUtils {

    /**
     * 获取 IPV4和IPV6信息
     * @return
     * @throws SocketException
     */
    public static List<String> getIpv4_Ipv6() throws SocketException {
        // Ipv4和Ipv6地址
        List<String> iPv4_iPv6 = new ArrayList<>(8);
        // 获取所有的网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            // 获取每个网络接口的IP地址
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                // 检查是否是IPv4地址
                if (inetAddress instanceof Inet4Address) {
                    iPv4_iPv6.add(new StringBuilder("IPv4: ").append(networkInterface.getName()).append("     ").append(inetAddress.getHostAddress()).toString());
                }
                // 检查是否是IPv6地址
                if (inetAddress instanceof Inet6Address) {
                    iPv4_iPv6.add(new StringBuilder("IPv6: ").append(networkInterface.getName()).append("     ").append(inetAddress.getHostAddress()).toString());
                }
            }
        }
        return iPv4_iPv6;
    }

    /**
     * 获取 IPV4信息
     * @return
     * @throws SocketException
     */
    public static List<String> getIpv4() throws SocketException {
        // Ipv4地址
        List<String> iPv4 = new ArrayList<>(8);

        // 获取所有的网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            // 获取每个网络接口的IP地址
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                // 检查是否是IPv4地址
                if (inetAddress instanceof Inet4Address) {
                    iPv4.add(new StringBuilder("IPv4: ").append(networkInterface.getName()).append("     ").append(inetAddress.getHostAddress()).toString());
                }
            }
        }
        return iPv4;
    }

    /**
     * 获取 IPV4和IPV6信息
     * @return
     * @throws SocketException
     */
    public static List<String> getIpv6() throws SocketException {
        // Ipv4地址
        List<String> iPv6 = new ArrayList<>(8);

        // 获取所有的网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            // 获取每个网络接口的IP地址
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                // 检查是否是IPv6地址
                if (inetAddress instanceof Inet6Address) {
                    iPv6.add(new StringBuilder("IPv6: ").append(networkInterface.getName()).append("     ").append(inetAddress.getHostAddress()).toString());
                }
            }
        }
        return iPv6;
    }
}
