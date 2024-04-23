package com.springboot.ch3.di2;

import org.springframework.context.annotation.Bean;

public class AppConfig {
    @Bean
    public Car car() { // 이 메서드는 Car 타입의 빈을 생성하고 등록
        // map.put("car", new Car()); 이 코드와 같은 역할을 합니다. 즉, Car 객체를 생성하고 이를 "car"라는 이름으로 컨테이너에 등록
        Car car = new Car();
        return car;
    }

    @Bean
    public Engine engine() {
        // Engine 타입의 객체를 생성하고 "engine"이라는 이름으로 컨테이너에 등록
        return new Engine();
    }

    @Bean
    public Door door() {
        // Door 타입의 객체를 생성하고 "door"라는 이름으로 컨테이너에 등록
        return new Door();
    }
}
