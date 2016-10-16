package com.linguo.sharecenter.dao;

import com.linguo.sharecenter.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserNameAndEmail(String userName, String email);
}
