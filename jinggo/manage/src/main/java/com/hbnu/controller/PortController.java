package com.hbnu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {
    @Value("${server.port}")
    private String port; //服务器端口号

    @RequestMapping("/port")
    public String getPort() {
        return "当前访问的端口号" + port;
    }
}
