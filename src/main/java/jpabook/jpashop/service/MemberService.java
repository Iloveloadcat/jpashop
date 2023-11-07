package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //jpa모든 데이터변경이나 로직들은 가급적 트랜젝션 안에서 실행되어야 한다
@RequiredArgsConstructor //final이 붙은 필드만 생성자를 만들어 준다.
public class MemberService {

    //@Autowired //스프링 빈에 등록되어있는 MemberRepository를 인젝션 해준다. => 필드인젝션
    // 단점은 memberRepository를 변경하거나 테스트할 수가 없음.
    private final MemberRepository memberRepository;

 /*   @Autowired //생성자 인젝션
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //=> 이 생성자를 만들어주는것과 같은 동작을 하는 어노테이션이 @AllArgsConstructor
 */

    /**
    * 회원 가입
    */
    @Transactional //데이터의 변경이 일어나야 하므로 별도로 어노테이션 선언
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId(); //영속성컨텍스트에 의해 DB에 들어간 시점이 아니여도 값을 보장해줌
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /**
     * 회원 전체조회
     */
    //@Transactional(readOnly = true) //읽기 전용인 메소드에 넣어주면 성능이 좀 더 좋아짐.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
