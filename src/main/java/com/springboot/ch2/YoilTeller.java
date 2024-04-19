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
    @RequestMapping("/getYoil")
    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 입력
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");


            int yyyy = Integer.parseInt(year);
            int mm = Integer.parseInt(month);
            int dd = Integer.parseInt(day);

            // 2. 작업 - 요일 계산
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(yyyy, mm - 1, dd); // 월은 0부터 시작하므로 1을 빼줍니다.

            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

            // 3. 출력
            response.setCharacterEncoding("ms949");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("year = " + year);
            out.println("month = " + month);
            out.println("day = " + day);
            out.println("yoil = " + yoil);
            out.println("</body>");
            out.println("</html>");

    }
}
