package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") //자식 테이블 중 어느 테이블을 조회해야하는지 구분하기 위해 DTYPE이란 구분 컬럼을 사용
@Getter @Setter
public abstract class Item { //실제 생성되는 테이블이 아니기 때문에 추상클래스로 선언
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * stock 증가
     * */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     * */
    public void removeStock(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if(resultStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = resultStock;
    }
}

