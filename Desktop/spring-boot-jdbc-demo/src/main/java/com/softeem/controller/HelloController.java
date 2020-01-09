package com.softeem.controller;

import org.springframework.web.bind.annotation.RestController;


/**
 * 如果，Controller 类的脑袋上面标注的是 @RestController 注解，而非 @Controller 注解
 * 那么，Springboot 会使用默认的 jackson 库，
 * 将方法的返回值（对象、对象的集合）转换成 JSON 格式字符串，发回给客户端（浏览器、postman、手机app等）
 *
 */
@RestController
public class HelloController {

//    @RequestMapping("/hello")
//    public Student demo() {
//        Student student = new Student(9527, "tom", 19, "192xxxxxxxx");
//        return student;
//    }
}
