package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    /**
     * Optional<T> 클래스
     * (1) Integer나 Double 클래스 처럼 'T'타입의 객체를 포장해주는 래퍼 클래스
     * (2) Optional 인스턴스는 모든 타입의 참조 변수를 저장 가능
     * (3) Optional 객체 사용시, 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피 가능
     * (복잡한 조건문 없이 null값 발생 예외 처리 가능)
     *
     * Member은 id,name의 getter, setter 정보를 담고 있음
     * */

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll();

}
