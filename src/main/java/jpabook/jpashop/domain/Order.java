package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.collection.internal.PersistentList;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne //Order와 Member는 다대일관계
    @JoinColumn(name = "memeber_id") //어떤컬럼으로 member테이블과 조인을 걸건지
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    private OrdetStatus status; //주문상태[order, cancel]
}
