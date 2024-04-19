package com.springboot.ch2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/*
    년원일을 입력하면 요일을 알려주는 원격 프로그램
 */
@Controller
public class YoilTeller {
    @RequestMapping( "/getYoil")
    public void main(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 입력
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);
        // 2. 작업 - 요일 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear(); // cal 의 모든 필드를 초기화 (안해줘도 되지만 정확한 날짜를 계산하기 위해 초기화)
        cal.set(yyyy, mm-1, dd); // 월(mm)은 0부터 11이기 떄문에 1을 뺴줘야함!

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7 을 반환. 1 : 일요일 2: 월요일 . . . . .
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1); // 1 ~ 7 -> 0 ~ 6


        // 3. 출력 - 작업 결과를 브라우저에 전송
        response.setCharacterEncoding("ms949"); // 한글 윈도우는 ms949
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("year = " + year);
        out.println("month = " + month);
        out.println("day = " + day);
        out.println("yoil = " + yoil);
        out.println("<body>");
        out.println("</html>");


    }
}
