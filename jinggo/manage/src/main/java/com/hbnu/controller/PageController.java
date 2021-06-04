package com.hbnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 方式一：url:/page/{pageName}
     * restFul风格
     *  特点：参数之间用/分隔；参数用{}包裹；方法获取url中的参数需要通过@PathVariable注解
     * restFul风格中，ur1参数较多的情况如何接收参数? ur1: /page/rose/24/18/American
     * 可以通过对象去接收ur1中的参数，对象中的属性名称和前端传过来的参数名一致
     *
     * restFul高级格式:
     * 需求:用户发送url: userById 100实现查询用户信息或者删除用户信息
     *
     * http请求方式有8中，常见的有4中
     *  get:查询
     *  post :新增
     *  put :修改.
     *  delete:删除
     *
     * 方式二：url:/page?pageName=index
     *
     * @param pageName
     * @return
     */
    @RequestMapping("/page/{pageName}")
    public String page(@PathVariable String pageName) {
        return pageName;
    }
}
