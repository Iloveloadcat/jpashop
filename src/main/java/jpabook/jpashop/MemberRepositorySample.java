package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositorySample {

    @PersistenceContext //EntityManager를 Bean으로 주입시키기 위해 사용
    private EntityManager em; //특정 작업을 위해 데이터베이스에 액세스 하는 역할


    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }


}
