package com.example.demo;

import com.example.demo.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();
    
    public User update(User user);
    public User createOrUpdateUser(User entity) throws Exception;
    public void deleteUserById(Integer id) throws Exception;
    public User getUserById(Integer id) throws Exception;
}