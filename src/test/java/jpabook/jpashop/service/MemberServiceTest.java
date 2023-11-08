package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //junit 실행할때 스프링과 같이 엮어서 실행할거다 라고 명시
@SpringBootTest //스프링부트를 띄운 상태로 테스트를 하기위해 사용. 없으면 Autowired 실패 뜸
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@RollBack(false)를 넣으면 insert쿼리로그가 안보임
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setUsername("kim");
        //when
        Long saveId = memberService.join(member); //새로운 member객체를 저장하고 영속화


        //then
        //같은 엔티티 즉, pk값이 같으면 같은 영속성 컨텍스트안에서 똑같은 형태로 관리됨
        //member라는 엔티티에 1차 캐시가 저장되어있고, 그것을 saveId라는 식별자를 통해서 가져오기 때문에 같아서 true값을 반환
        assertEquals(member, memberRepository.findOne(saveId)); //saveId를 이용해 위에서 저장한 member객체를 다시 조회.
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setUsername("kim1");

        Member member2 = new Member();
        member2.setUsername("kim1");

        //when
        memberService.join(member1);
        memberService.join(member2);

/*        memberService.join(member1);
        try{
            memberService.join(member2);//예외처리
        }catch (IllegalStateException e) {
            return;
        }*/
        //then
        fail("예외가 발생해야 한다"); //위에서 예외가 발생하지 않았을 시 발생되는 예외처리
    }
}