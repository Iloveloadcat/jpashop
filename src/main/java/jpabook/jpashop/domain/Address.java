package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //JPA의 내장 타입이라는것으로 명시
@Getter
//@Setter를 만들지 않음으로써 값이 변경되는것을 방지.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //기본생성자를 만들어주어야 런타임하는 시점에 객체를 생성할 수 있다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
