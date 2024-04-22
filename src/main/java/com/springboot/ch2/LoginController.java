package com.springboot.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/login")
    public String showLogin() {
        return "login"; // login.html
    }

    // 하나의 메서드로 GET, POST 둘다 처리하는 경우 아래와 같이 배열로 처리
    //    @RequestMapping(value = "/login/login", method = {RequestMethod.GET, RequestMethod.POST})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String id, String pwd, Model model) throws Exception {
        // 1. id, pwd 확인
        if(loginCehck(id,pwd)){
        // 2. 일치하면 , userInfo.html
            model.addAttribute("id", id);
            model.addAttribute("pwd", pwd);
            return "userInfo"; // userInfo.html

        } else  {
        //    일치하지 않으면, login.html 이동
            String msg = URLEncoder.encode( "id 또는 pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/login?msg="+msg; // GET    URL다시쓰기
        }
    }

    private boolean loginCehck(String id, String pwd) {
        return id.equals("qwer") && pwd.equals("1234");
    }
}
