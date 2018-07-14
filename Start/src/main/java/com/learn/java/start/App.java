package com.learn.java.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.learn.java.bo.TestTransactionProgagationCourseBo;
import com.learn.java.bo.TestTransactionProgagationUserBo;
import com.learn.java.bo.UserManagerBo;
import com.zlx.user.dal.dao.CourseDO;
import com.zlx.user.dal.dao.UserDO;
import com.zlx.user.dal.sqlmap.UserDOMapper;

/**
 * Hello world!
 *  
 */
@RestController
@SpringBootApplication
@ComponentScan(basePackages = { "com.learn.java.bo.impl" })
@ImportResource("applicationContext.xml")
public class App {
   
	@Autowired
	private UserManagerBo userManagerBo;

	@Autowired
	private TestTransactionProgagationUserBo userBo;
	
	@Autowired
	private TestTransactionProgagationCourseBo courseBo;
	
	@RequestMapping("/home")
	String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/selecUser")
	String selecUser() {
		return JSON.toJSONString(userBo.select());
	}
	
	
	@RequestMapping("/addUser")
	String inserUser() {
		
		UserDO user = new UserDO();
		user.setAge(10);
		
		return userBo.insertUser(user) + "";
	}
	
	@RequestMapping("/addCourse")
	String addCourse() {
		
		CourseDO course = new CourseDO();
		course.setUserId(1);
		course.setCourseName("java");
		return courseBo.insertCourse(course) + "";
	}
	
	@RequestMapping("/testTransaction")
	String testTransaction() {
		
		UserDO user = new UserDO();
		user.setAge(10);;
		
		return userManagerBo.addNewUser(user) + "";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
