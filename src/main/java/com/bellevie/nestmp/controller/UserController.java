package com.bellevie.nestmp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bellevie.nestmp.db.dto.User;
import com.bellevie.nestmp.service.LogService;
import com.bellevie.nestmp.service.UserService;

/*
 * RESTful服务的测试例子
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;

	/*
	 * 不带参数的请求
	 * 获取所有用户
	 * 形如 http://localhost:8080/users/
	 */
    @RequestMapping(method=RequestMethod.GET)
    public List<User> getUsers(HttpServletRequest request, HttpServletResponse response){
    	logService.createLog(request);
    	
        List<User> list=userService.getAllUsers();
        return list;
    }
	
    /*
     * 根据url最后一个参数作为id
     * 查找用户
     * 形如 http://localhost:8080/users/1
     */
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User getUserInfo(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
    	logService.createLog(request);
    	
    	User user = userService.getUserInfo(id);
		if (user != null) {
			logger.info("user.getName():" + user.getName());
			logger.info("user.getAge():" + user.getAge());
		}
		return user;
	}
}
