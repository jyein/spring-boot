package com.study.springboot202210Hseonguk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 지금 만들고 있는것이 Controller 클래스이다
// Controller클래스를 만들때엔 밑에있는 어노테이션을 써야한다.
@Controller
public class Controller1 {

//    @RequestMapping(value = "/page1", method = RequestMethod.GET) 이녀석을 줄인것이 밑에있는 @GetMapping이다.

    @GetMapping("/page1")
    public String page1() {
        return "page1"; // "page1"은 html파일 이름이다.
    }

    @GetMapping("/page2")
    public String page2() {
        return "page2"; // "page1"은 html파일 이름이다.
    }


}