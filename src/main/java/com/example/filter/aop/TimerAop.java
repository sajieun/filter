package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class TimerAop {

    @Pointcut(value = "within(com.example.filter.controller.UserApiController)")
    public void timerPointCut(){}

    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");

    }
    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");

    }
    // 예외가 발생하지 않았을 때
    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("after returning");
    }
// 예외가 발생했을 때
    @AfterThrowing(value = "timerPointCut()", throwing = "tx")
    public void afterReturning(JoinPoint joinPoint, Throwable tx){
        System.out.println("after throwing");
    }

    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("메소드 실행 전");
        Arrays.stream(joinPoint.getArgs()).forEach(
                it->{
                    if (it instanceof UserRequest){
                        var tempUser = (UserRequest)it;
                        var phoneNumber = tempUser.getPhoneNumber().replace("-","");
                        tempUser.setPhoneNumber(phoneNumber);
                    }
                    System.out.println(it);
                }
        );
        var newObjs = Arrays.asList(
                new UserRequest()
        );
        var stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(newObjs.toArray());
        stopWatch.stop();
        System.out.println("총 종료 시간 : "+stopWatch.getTotalTimeMillis());
        System.out.println("메소드 실행 후");
    }
}
