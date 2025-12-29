package com.example.bottomnavigationapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import java.net.InetAddress

fun getLocalIpAddress(context: Context): String? {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // 获取当前活跃的网络
    val network = connectivityManager.activeNetwork ?: return null
    val linkProperties: LinkProperties? = connectivityManager.getLinkProperties(network)

    // 遍历所有 IP 地址，找到第一个非本地回环的 IPv4 地址
    val ipAddress = linkProperties?.linkAddresses?.find {
        it.address.isSiteLocalAddress && !it.address.isLoopbackAddress && it.address.hostAddress?.contains(":") == false
    }?.address?.hostAddress

    return ipAddress
}