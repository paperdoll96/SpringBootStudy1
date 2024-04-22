package com.springboot.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/*
    년원일을 입력하면 요일을 알려주는 원격 프로그램
 */
@Controller
public class YoilTeller {
    @RequestMapping( "/getYoil")
    public String main(@ModelAttribute  MyDate myDate, Model model) throws Exception {
                    // 위 어노테이션이 있으면 아래 일일히 저장 하지 않아도 됨!

        // 2. 작업 - 요일 계산
        char yoil = getYoil(myDate);

        // 작업한 결과를 Model에 저장 (디스팩쳐 서블릿이 Model을 View로 전달)
//        model.addAttribute("year", myDate.getYear());
//        model.addAttribute("month", myDate.getMonth());
//        model.addAttribute("day", myDate.getDay());
        


        return "yoil"; // yoil.html - 뷰의 이름을 반환

    }

    @ModelAttribute("yoil")
    private char getYoil(MyDate myDate) {
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear(); // cal 의 모든 필드를 초기화 (안해줘도 되지만 정확한 날짜를 계산하기 위해 초기화)
        cal.set(myDate.getYear(), myDate.getMonth()-1, myDate.getDay()); // 월(mm)은 0부터 11이기 떄문에 1을 뺴줘야함!

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7 을 반환. 1 : 일요일 2: 월요일 . . . . .
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1); // 1 ~ 7 -> 0 ~ 6
        return yoil;
    }
}
