package com.hmoro.service;

import com.hmoro.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class BookService {
//    @Qualifier("bookDao2")
//    @Autowired(required = false)

//    @Resource(name = "bookDao2")
    @Inject
    private BookDao bookDao;

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
