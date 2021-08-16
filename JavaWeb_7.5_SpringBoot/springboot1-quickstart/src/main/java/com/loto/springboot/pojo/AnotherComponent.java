package com.loto.springboot.pojo;

import lombok.Data;

import java.net.InetAddress;

/**
 * 将属性绑定到控件之外的第三方组件
 */
@Data
public class AnotherComponent {
    // 是否启用
    private Boolean enabled;

    // IP地址
    private InetAddress remoteAddress;

    @Override
    public String toString() {
        return "AnotherComponent{" +
                "enabled=" + enabled +
                ", remoteAddress=" + remoteAddress +
                '}';
    }
}
