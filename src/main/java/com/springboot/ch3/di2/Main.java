package com.springboot.ch3.di2;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

// Car 클래스 정의
class Car {
    @Autowired Engine engine; // Engine 타입의 객체를 자동으로 주입받습니다. @Autowired는 타입에 따라 의존성을 주입합니다.

    @Resource Door door; // Door 타입의 객체를 이름에 따라 주입받습니다. @Resource는 이름을 기반으로 의존성을 주입합니다.

    // engine을 설정하는 setter 메소드
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    // door을 설정하는 setter 메소드
    public void setDoor(Door door) {
        this.door = door;
    }

    // Car 객체의 상태를 문자열로 반환하는 toString 메소드
    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

// Car를 상속받는 SportCar 클래스 정의
class SportCar extends Car {
    @Override
    public String toString() {
        return "SportCar{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

// Engine 클래스 정의
class Engine {}

// Door 클래스 정의
class Door {}

// 메인 클래스
public class Main {

    public static void main(String[] args) {
        // AppConfig 클래스를 사용해 AppContext 객체를 초기화
        AppContext ac = new AppContext(AppConfig.class);
        Car car = (Car) ac.getBean("car"); // 이름 "car"에 해당하는 객체를 반환합니다. byName 방식으로 객체를 조회
        Car car2 = (Car) ac.getBean(Car.class); // Car 타입의 객체를 반환합니다. byType 방식으로 객체를 조회
        Engine engine = (Engine) ac.getBean("engine"); // 이름 "engine"에 해당하는 객체를 반환
        Door door = (Door) ac.getBean(Door.class); // Door 클래스 타입에 맞는 객체를 반환

        // 출력문을 통해 객체들의 상태를 확인
        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);

    }
}
