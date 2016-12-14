package com.linguo.sharecenter.controller;

import com.linguo.sharecenter.dao.BookRepository;
import com.linguo.sharecenter.dao.UserRepository;
import com.linguo.sharecenter.model.Book;
import com.linguo.sharecenter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ShareBookController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Value("${book.thumb.url}")
    private String bookThumbUrl;

    @Value("${app.url}")
    private String appUrl;

    @RequestMapping("/index")
    public String index(@RequestParam(value = "userid") String userId,
                        @RequestParam(value = "bookGuid") String bookGuid,
                        Map<String, Object> model) {
        System.out.println("loading data ");
        User user = userRepository.findOne(userId);
        Book book1 = bookRepository.findOne("W04xELgybD");
        Book book = bookRepository.findByGuid(bookGuid);
        model.put("user", user);
        model.put("book", book);
        model.put("appUrl", appUrl);
        if(book != null) {
            List<String> bookCovers = new ArrayList<>();
            bookCovers.add(String.format(bookThumbUrl, book.getThumbId()));
            model.put("bookCovers", bookCovers);
            model.put("book", book);
            model.put("user", user);
        }

        return "book";
    }


    @RequestMapping("/demo")
    public String demo(Map<String, Object> model) {
        System.out.println("loading data2");
        model.put("message", "Demo!");
        return "index2";
    }

}
