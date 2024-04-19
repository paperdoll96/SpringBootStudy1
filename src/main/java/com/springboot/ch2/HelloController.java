package com.springboot.ch2;

// 원격프로그램은 다른 컴퓨터에서 바로 실행 할수 없어서 아래와 같은 단계를 걸친다.
// 1. 원격 프로그램으로 등록!
// 2. URL 과 메서드를 연결

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // 1. 원격 프로그램으로 등록!
public class HelloController { // 원격 프로그램
    // 2. URL 과 메서드를 연결
    @RequestMapping("/hello")
    public void main() { // static이 없어도 되는 이유는 톰캣이 자동으로 생성해줌
        System.out.println("Hello~");
    }
}
