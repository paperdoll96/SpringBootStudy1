package com.springboot.ch3.di2;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext {
    Map map = new HashMap(); // 객체를 저장할 맵 선언

    // 기본 생성자: 맵에 일부 객체를 미리 넣어둠
    AppContext() {
        map.put("car", new SportCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }
    AppContext(Class clazz) {
        Object config = null;
        try {
            config = clazz.newInstance(); // 설정 클래스의 인스턴스 생성
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method[] methods = clazz.getDeclaredMethods(); // 설정 클래스의 모든 메서드 가져오기

        for (Method m : methods) {
            // 메서드에 선언된 어노테이션 검사
            for (Annotation anno : m.getDeclaredAnnotations()) {
                if (anno.annotationType() == Bean.class) { // @Bean 어노테이션이 있을 경우
                    try {
                        map.put(m.getName(), m.invoke(config, null)); // 메서드 호출 결과를 맵에 추가
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        doAutowired(); // @Autowired 어노테이션 처리
        doResource();  // @Resource 어노테이션 처리
    }

    // @Resource 어노테이션 처리: 각 객체의 필드에 @Resource 어노테이션이 있을 경우, 해당 필드에 맵의 객체를 할당
    private void doResource() {
        for (Object bean : map.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.getAnnotation(Resource.class) != null) {
                    try {
                        field.set(bean, getBean(field.getType())); // 필드에 맵의 객체를 할당
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    // @Autowired 어노테이션 처리: 각 객체의 필드에 @Autowired 어노테이션이 있을 경우, 해당 필드에 맵의 객체를 할당
    private void doAutowired() {
        for (Object bean : map.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.getAnnotation(Autowired.class) != null) {
                    try {
                        field.set(bean, getBean(field.getType())); // 필드에 맵의 객체를 할당
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    // 빈 아이디에 해당하는 객체 반환
    public Object getBean(String id) {
        return map.get(id);
    }

    // 클래스 타입에 해당하는 객체 반환
    public Object getBean(Class clazz) {
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }
}
