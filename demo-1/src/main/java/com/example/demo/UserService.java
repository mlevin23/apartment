package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {

        return (List<User>) repository.findAll();
    }
    
    @Override
    public User update(User user)
    {
    	return repository.save(user);
    }
    
    @Override
    public User getUserById(Integer id) throws Exception
    {
        Optional<User> user = repository.findById(id);
         
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("No user record exists for given id");
        }
    }
     
    @Override
    public User createOrUpdateUser(User entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
            
            return entity;
        } 
        else
        {
            Optional<User> user = repository.findById(entity.getId());
             
            if(user.isPresent()) 
            {
                User newEntity = user.get();
                newEntity.setName(entity.getName());
                newEntity.setBirthday(entity.getBirthday());
                repository.deleteById(newEntity.getId());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    @Override
    public void deleteUserById(Integer id) throws Exception 
    {
        Optional<User> user = repository.findById(id);
         
        if(user.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new Exception("No user record exists for given id");
        }
    } 
}