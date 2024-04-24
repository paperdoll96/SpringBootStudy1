package com.springboot.ch3.di3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// 스프링 설정 클래스를 나타내는 어노테이션
@Configuration
@ComponentScan
public class AppConfig2 {
//    // Car 빈을 정의하는 메서드
//    @Bean
////    @Scope("singleton")  디폴트가 싱글톤 한번 생성된 객체를 계속해서 재사용
//    Car car() {
//        return new Car();
//    }
//
//    // Engine 빈을 정의하는 메서드
//    @Bean
//    // 빈의 스코프를 설정하는 어노테이션, 디폴트는 싱글톤
//    @Scope("prototype") // 프로토타입 스코프로 설정하여 매번 새로운 객체 생성
//    Engine engine() {
//        return new Engine();
//    }
//
//    // Door 빈을 정의하는 메서드
//    @Bean
//    Door door() {
//        return new Door();
//    }
}