package com.example.recommend.service;

import com.example.recommend.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> queryAllUser();
    Page<User> findAll(Pageable pageable);


}
