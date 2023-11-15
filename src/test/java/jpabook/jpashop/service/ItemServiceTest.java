package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;


    @Test
    @Rollback(value = false)
    public void 상품추가() throws Exception {
        //given
        Book book = new Book();
        book.setName("Effective Java");
        book.setPrice(30000);
        book.setStockQuantity(10);
        book.setAuthor("Joshua Bloch");
        //when
        itemService.saveItem(book);

        //then
        assertNotNull(book.getId());
    }


}