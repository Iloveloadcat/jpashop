package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;

    @Embedded //내장타입으로 매핑했다라는것으로 명시
    private Address address;

    @OneToMany(mappedBy = "member") //Member의 입장에서 주문은 여러개이므로 일대다
    //mappedBy = "member" => order테이블에 있는 member필드에 의해 매핑이 됨을 명시
    private List<Order> order = new ArrayList<>();



}

