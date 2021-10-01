package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
// --> Spring 빈에 등록, 또는 SpringConfig파일에 등록(더 선호)
public class TimeTraceAop {

    /**
     * 호출시간 로직 작성
     * @Around("execution(* hello.hellospring..*(..))")
     * --> hello.hellospring 패키지 하위에 전부 적용하라는 의미
     * 원하는 곳에 적용가능
     * */
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            // 다음 프로시저로 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}
