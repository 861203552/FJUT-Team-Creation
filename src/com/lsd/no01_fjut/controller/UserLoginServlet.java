/**
 * 
 */
package com.lsd.no01_fjut.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsd.no01_fjut.exception.ExpiredException;
import com.lsd.no01_fjut.exception.SignException;
import com.lsd.no01_fjut.util.Auth;
import com.lsd.no01_fjut.util.JSONUtil;
import com.lsd.no01_fjut.util.Message;
import com.lsd.no01_fjut.util.Token;

/*** <p>Title:UserLoginServlet </p>
 * <p>Description: </p>
 * @author xiaolong
 * @date 2017 下午5:31:42
 */
public class UserLoginServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		Message message = new Message(1,"ok");
		if("signin".equals(act)) {
			Map<String,Object> claims = new HashMap<String,Object>();
			if(!"admin".equals(request.getParameter("username"))){
				message = new Message(-1, "用户名错误");
				String json = JSONUtil.object2json(message);
				response.getWriter().write(json.toString());
			}
			else if(!"111111".equals(request.getParameter("password")))
			{
				message = new Message(-1, "密码错误");
				String json = JSONUtil.object2json(message);
				response.getWriter().write(json.toString());
			}
			else
			{
				//token部分
				claims.put("id", 314190001);
				claims.put("username", request.getParameter("username"));
				claims.put("password",request.getParameter("password"));
				claims.put("role","teacher");
				String s = Token.jsonBuilder(claims);
				//数据部分
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("role", "teacher");
				message.setData(data);
				response.setHeader("Authorization", s);
				String json = JSONUtil.object2json(message);
				response.getWriter().write(json.toString());
			}
		}

	}


	public void init() throws ServletException {
		// Put your code here
	}

}
