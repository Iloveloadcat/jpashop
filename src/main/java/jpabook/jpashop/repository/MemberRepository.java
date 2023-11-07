package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor //Spring Data JPA가 이런방식으로 되게끔 지원을 해 줌.
public class MemberRepository {
    /*
    @PersistenceContext
    private EntityManager em;
    */

    private final EntityManager em; //repository도 생성자를 만들어줄 수 있고 service랑 일관되게 사용 가능

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
