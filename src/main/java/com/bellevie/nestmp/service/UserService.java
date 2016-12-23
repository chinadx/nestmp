package com.bellevie.nestmp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellevie.nestmp.db.dao.UserMapper;
import com.bellevie.nestmp.db.dto.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(int id){
        User user=userMapper.selectByPrimaryKey(id);
        return user;
    }

    public List<User> getAllUsers() {
    	return userMapper.selectAll();
    }
}