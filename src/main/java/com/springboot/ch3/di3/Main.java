package com.springboot.ch3.di3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

class Car {
    Engine engine;

    Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

class Engine { }

class Door {}

public class Main {
    public static void main(String[] args) {
        // 스프링 컨테이너를 생성, 설정 정보는 AppConfig 클래스를 사용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // Car 객체를 스프링 컨테이너에서 이름 "car"로 가져옴. 형 변환 없이 바로 Car 타입으로 받음
        Car car = ac.getBean("car", Car.class);

        // Engine 객체를 스프링 컨테이너에서 타입만 지정하여 가져옴
        Engine engine = ac.getBean(Engine.class);
        Engine engine2 = ac.getBean(Engine.class);
        Engine engine3 = ac.getBean(Engine.class);

        // 생성된 객체 정보 출력
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("engine2 = " + engine2);
        System.out.println("engine3 = " + engine3);

        // 스프링 컨테이너에 등록된 빈의 개수 출력
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        // 스프링 컨테이너에 등록된 모든 빈의 이름 출력
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames()));

        // "engine" 이름을 가진 빈이 컨테이너에 존재하는지 여부 확인
        System.out.println("ac.containsBean(\"engine\") = " + ac.containsBean("engine"));
        // "car" 이름의 빈이 싱글톤인지 여부 확인
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
        // "car" 이름의 빈이 프로토타입(요청할 때마다 새로 생성)인지 여부 확인
        System.out.println("ac.isPrototype(\"car\") = " + ac.isPrototype("car"));
    }
}
