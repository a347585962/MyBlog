package com.songlin.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.songlin.model.User;

public class LoginController extends Controller {

	public void index() {

		this.render("/login.jsp");

	}

	public void login() {
		System.out.println("11111");
		String userName = this.getPara("username");
		String password = this.getPara("password");

		System.out.println(userName + password);

		List<User> user = User.dao.find("select * from user_table where userName = ? and password = ?", userName,
				password);
		
		if (user.size() == 0) {
			System.out.println("login111");
			this.render("/blog.jsp");

		} else {
			List<User> user1 = User.dao.find("select * from user_table");
			System.out.println(user1);
			
			for(User u : user1){
				
				
//				u.getStr(attr)
				System.out.println(u.getStr("userName")+ u.getStr("password"));
			}
			
			this.render("/login.jsp");

		}

	}

}
