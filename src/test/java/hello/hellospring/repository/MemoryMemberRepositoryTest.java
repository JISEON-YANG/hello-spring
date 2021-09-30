package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 추가조건: DB와 연동
 * @Transaction을 class에 추가하면 스프링이 제일 먼저 Transaction부분을 실행
 * 데이터 입력 후 DB에 들어간 것을 확인할 수 있음
 * 그후 롤백을 하기 때문에, 추가했던 데이터들은 자동 삭제된 것을 확인할 수 있음
 * */

class MemoryMemberRepositoryTest {

    /**
     * org.junit.jupiter.api --> Assertions.assertEqauls
     * org.assertj.core.api --> Assertions.assertThat
     * ---------------------------------------------------
     * Assertions.assertEqauls(x,y)
     * Assertions.assertThat(y).isEqualTo(x)
     * 객체 x와 y가 일치함을 확인
     * x(예상값), y(실제값)가 같으면 테스트 통과
     * */

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * @AfterEach 메소드가 실행이 끝날 때마다 실행되는 것, 롤백메소드
     * */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        // Member result = repository.findByName("spring2").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    /**
     * findAll()은 데이터 전부 비교
     * repository에 각각 저장
     * 리스트 사이즈가 2와 같으면 참
     * */

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
