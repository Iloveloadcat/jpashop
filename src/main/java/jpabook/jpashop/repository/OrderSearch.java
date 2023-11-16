package jpabook.jpashop.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private String orderStatus;//주문상태(ORDER ,CANCEL)
}
