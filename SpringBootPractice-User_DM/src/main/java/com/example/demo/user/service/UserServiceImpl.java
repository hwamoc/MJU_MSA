package com.example.demo.user.service;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.model.RestaurantVO;
import com.example.demo.user.model.UserVO;

@Service("com.example.demo.user.service.UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO mUserDAO;
	@Autowired
	ResDAO mResDAO;
	
	// 로그인
	@Override
	public UserVO login(UserVO member) throws Exception {


		if(mUserDAO.check_id(member.getUser_id()) == 0) {
			return null;
		} else {
			String pw = member.getUser_password();
			member = mUserDAO.userLogin(member.getUser_id());
			if(!member.getUser_password().equals(pw)) {
				return null;
			}
		return member;
		}
	}
	
	@Override
	   public int check_id(String user_id) throws Exception {
	      return mUserDAO.check_id(user_id);
	   }
	
	@Override
	public void logout(HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("location.href=document.referrer;");
		out.println("</script>");
		out.close();
		
	}
	
	public List<UserVO> userListService() throws Exception {

		return mUserDAO.userList();
	}


	public void userSignUpService(UserVO user) throws Exception {

		mUserDAO.userInsert(user);
	}

	public UserVO userSelectService(String user_id) {

		return mUserDAO.userSelect(user_id);
	}

	public void userModifyService(UserVO member) {

		mUserDAO.userModify(member);;
	}

	public void userDeleteService(String user_id) {
		mUserDAO.userDelete(user_id);		
	}

	public List<RestaurantVO> myResListService(String user_id) {
		return mResDAO.myResList(user_id);
	}

	public void myresInsert(RestaurantVO myRes) {
		mResDAO.myResInsert(myRes);
		
	}

	
	
}
