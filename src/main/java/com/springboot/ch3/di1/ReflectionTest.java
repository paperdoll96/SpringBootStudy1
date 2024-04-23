package com.springboot.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired; // 스프링의 자동 주입 어노테이션
import org.springframework.util.StringUtils; // 스프링 유틸 클래스, 문자열 조작에 사용

import java.lang.annotation.Annotation; // 자바 기본 어노테이션 처리 클래스
import java.lang.reflect.Field; // 클래스의 필드 정보를 다루는 리플렉션 API
import java.lang.reflect.Method; // 클래스의 메서드 정보를 다루는 리플렉션 API

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Car car = new Car(); // Car 클래스의 인스턴스 생성
        Class carClass = car.getClass(); // 인스턴스로부터 해당 인스턴스의 Class 객체를 가져옴

        carClass = Car.class; // 클래스 리터럴을 통해 직접 Class 객체를 가져옴, 컴파일 타임에 결정됨
        carClass = Class.forName("com.springboot.ch3.di1.Car"); // 문자열 이름을 통해 Class 객체를 로드, 런타임에 결정됨

        // Class 객체를 통해 새로운 인스턴스 생성
        Car car2 = (Car) carClass.newInstance();
        System.out.println("car2 = " + car2);

        // getDeclaredFields()는 해당 클래스에서 선언된 모든 필드를 Field 객체의 배열로 반환
        Field[] mvArr = carClass.getDeclaredFields();
        // getDeclaredMethods()는 해당 클래스에서 선언된 모든 메서드를 Method 객체의 배열로 반환
        Method[] methodArr = carClass.getDeclaredMethods();

        // 클래스에 정의된 모든 메서드 출력
        for (Method method : methodArr) {
            System.out.println(method.getName() + " : method");
        }

        // 클래스에 정의된 모든 필드 출력
        for (Field mv : mvArr) {
            System.out.println(mv.getName() + " : field");
        }

        // 특정 메서드를 찾아 해당 메서드를 호출
        Method method = carClass.getMethod("setEngine", Engine.class);
        method.invoke(car, new Engine()); // setEngine 메서드를 호출, Engine 인스턴스를 생성해 매개변수로 제공
        System.out.println("car = " + car);

        // 모든 필드에 대해 setter 메서드를 동적으로 호출
        for (Field mv : mvArr) {
            System.out.println("mv = " + mv);
            String methodName = "set" + StringUtils.capitalize(mv.getName()); // 첫 글자를 대문자로 변경하여 메서드 이름 생성
            System.out.println("methodName = " + methodName);
            method = carClass.getMethod(methodName, mv.getType()); // 해당 타입의 setter 메서드를 찾음
            method.invoke(car, mv.getType().newInstance()); // 해당 필드 타입의 새 인스턴스를 생성하고 메서드를 호출
        }

        // 모든 필드를 순회하며 @Autowired 어노테이션 확인
        for (Field mv : mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations(); // 필드에 선언된 모든 어노테이션을 배열로 반환
            for (Annotation anno : annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                System.out.println("anno.annotationType().getSimpleName() = " + anno.annotationType().getSimpleName());
                System.out.println(anno.annotationType() == Autowired.class); // 어노테이션이 Autowired 인지 확인
            }
        }

        // Autowired 어노테이션이 붙은 필드에 대해 setter 메서드 호출
        car = new Car(); // Car 객체를 다시 생성
        for (Field mv : mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations(); // 필드에 선언된 모든 어노테이션을 배열로 반환
            for (Annotation anno : annoArr) {
                if (anno.annotationType() == Autowired.class) { // 어노테이션이 Autowired일 경우
                    String methodName = "set" + StringUtils.capitalize(mv.getName()); // setter 메서드 이름 생성
                    method = carClass.getMethod(methodName, mv.getType()); // 메서드 가져오기
                    method.invoke(car, mv.getType().newInstance()); // 새 인스턴스 생성 및 메서드 호출
                }
            }
        }
        System.out.println("car = " + car); // 최종적으로 수정된 Car 객체 출력
    }
}
