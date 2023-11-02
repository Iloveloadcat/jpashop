package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional //EntityManager를 사용하는 모든 데이터의 변경은 transaction이 필요하다.
    //테스트케이스에서 @Transactional이 있으면 테스트가 끝난후 바로 롤백을 처리함. 왜냐하면, 데이터가 들어가서 커밋되버리면 반복적인 테스트를 못하기 때문이다.
    @Rollback(value = false) //@Transactional이 자동 롤백되는것을 막는 어노테이션
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("test");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }

}