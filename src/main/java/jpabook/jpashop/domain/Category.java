package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany //실무에서는 사용하지 않는것을 추천. 중간테이블 컬럼추가가 어렵고, 복잡한 쿼리를 실행하기 어렵기 때문.
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), //중간테이블의 category_id
            inverseJoinColumns = @JoinColumn(name = "item_id")//item테이블의 item_id컬럼 매핑
    ) //일대다, 다대일로 풀어내기위한 중간테이블이 필요


    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


}
