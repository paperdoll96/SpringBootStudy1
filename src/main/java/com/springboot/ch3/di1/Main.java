package com.springboot.ch3.di1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// Car 클래스 정의: 자동차의 기본 형태를 나타내는 클래스
class Car {}

// Engine 클래스 정의: 자동차의 엔진을 나타내는 클래스
class Engine {}

// Door 클래스 정의: 자동차의 문을 나타내는 클래스
class Door {}

// SportCar 클래스: Car 클래스를 상속받아 스포츠 카 특성을 갖는 클래스
class SportCar extends Car {}

// Truck 클래스: Car 클래스를 상속받아 트럭 특성을 갖는 클래스
class Truck extends Car {}

// Main 클래스: 프로그램의 실행 로직을 포함하는 주 클래스
public class Main {
    public static void main(String[] args) throws Exception {
        // getObject 메서드를 통해 Car 객체의 인스턴스를 생성
        Car car = (Car)getObject("car");
        // getObject 메서드를 통해 Engine 객체의 인스턴스를 생성
        Engine engine = (Engine) getObject("engine");
        // 생성된 객체 정보 출력
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

    // getObject 메서드: 외부 설정 파일에서 클래스 이름을 읽고 해당 클래스의 인스턴스를 생성
    static Object getObject(String key) throws Exception {
        Properties prop = new Properties(); // 설정값을 저장할 Properties 객체 생성
        Class clazz = null; // 클래스 타입을 저장할 변수

        try {
            // config.txt 파일을 읽어 Properties 객체에 로드
            prop.load(new FileReader("config.txt"));
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 예외 처리
            System.out.println("설정 파일을 찾을 수 없습니다.");
            throw new FileNotFoundException("config.txt 파일이 없습니다.");
        }

        // Properties 객체에서 key에 해당하는 값을 읽어옴
        String className = prop.getProperty(key);
        if (className == null) {
            // 설정된 클래스 이름이 없을 경우 예외 처리
            throw new IllegalArgumentException(key + "에 해당하는 클래스 이름이 설정되지 않았습니다.");
        }

        // 문자열로 받은 클래스 이름을 실제 클래스 객체로 변환
        clazz = Class.forName(className);

        // 클래스의 인스턴스를 생성하여 반환
        return clazz.newInstance();
    }

    // getCar 메서드: SportCar 인스턴스를 생성하여 반환
    static Car getCar() {
        return new SportCar();
    }
}
