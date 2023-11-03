package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL => 디폴트이고 1,2,3..숫자로 들어감 이러면 기존 상태값이 밀려서 문제가되기때문에 꼭 String으로 사용
    private DeliveryStatus status; //READY, COMP
}
