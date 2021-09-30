package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    /**
     * member 정보를 담을 store 변수 생성
     * sequence	0,1,2 등 키값을 생성
     * */

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;

    /**
     * member.setId(++sequence): member의 name이 들어올 때마다 id 값 증가(id 값을 시스템에서 지정해준 것)
     * 세팅한 id를 store(map)에 저장
     * 저장된 결과 반환
     * */

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * Optional 객체 생성
     * (1) of()	null이 아닌 명시된 값을 가지는 Optional 객체를 반환
     * 	of()메소드를 통해 생성된 Optional 객체에 null이 저장되면 NullPointException 예외가 발생
     * (2) ofNullable()	null이 될 가능성이 있다면, 사용
     * 	null이 아니면 명시된 값을 가지는 Optional 객체를 반환
     * 	null이면 비어있는 Optionval 객체를 반환
     *  get() ==> Optional 객체값 가져오기
     * */

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * store에서 람다 루프로 돌림
     * member에서 가져온 Name과 파라메타로 받은 Name이 같은 경우에 필터링
     * 루프를 돌면서 하나라도 찾으면 반환: findAny()
     * 만약 없으면, Optional에 null이 포함되어 반환되는 것
     * */

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    /**
     * store의 value를 반환(member를 반환)
     * (위에는 Map이지만 List 사용)
     * */

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 전체 테스트 클래스를 동작하게 되면 에러가 발생할 수 있다.
     * 모든 테스트는 순서를 보장하지 않기 때문이다.
     * 그렇기 때문에, 테스트가 끝나고 나면 데이터를 클리어해줘야 한다.
     * --------------------------------------------------------
     * 기존	구현 클래스	→	테스트 코드
     * TDD(테스트 주도 개발)	테스트 코드	→	구현 클래스
     * */

    public void clearStore(){
        store.clear();
    }
}
