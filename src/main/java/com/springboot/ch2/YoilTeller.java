package com.springboot.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/*
    년원일을 입력하면 요일을 알려주는 원격 프로그램
 */
@Controller
public class YoilTeller {
    @RequestMapping( "/getYoil")
    public String main(int year, int month, int day, Model model) throws Exception {


        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear(); // cal 의 모든 필드를 초기화 (안해줘도 되지만 정확한 날짜를 계산하기 위해 초기화)
        cal.set(year, month-1, day); // 월(mm)은 0부터 11이기 떄문에 1을 뺴줘야함!

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7 을 반환. 1 : 일요일 2: 월요일 . . . . .
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1); // 1 ~ 7 -> 0 ~ 6

        // 작업한 결과를 Model에 저장 (디스팩쳐서블릿이 Model을 View로 전달)
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);


        return "yoil"; // yoil.html - 뷰의 이름을 반환




    }
}
