package com.springboot.ch2;

// 원격프로그램은 다른 컴퓨터에서 바로 실행 할수 없어서 아래와 같은 단계를 걸친다.
// 1. 원격 프로그램으로 등록!
// 2. URL 과 메서드를 연결

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 1. 원격 프로그램으로 등록!
public class HomeController { // 원격 프로그램
    // 2. URL 과 메서드를 연결
    @RequestMapping("/")
    public String main()
    {
        return "index"; // templates/index.html
    }

    @GetMapping("/")
    public String test()
    {
        return "test"; // templates/index.html
    }
}
