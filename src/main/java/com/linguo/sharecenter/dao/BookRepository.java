package com.linguo.sharecenter.dao;

import com.linguo.sharecenter.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

    public Book findByGuid(String guid);

}
