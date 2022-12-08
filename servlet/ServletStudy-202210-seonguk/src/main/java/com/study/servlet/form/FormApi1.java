package com.study.servlet.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/form/1")
public class FormApi1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get요청옴!!");
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("phone"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("addresss"));
		System.out.println(request.getParameter("pw"));
		// getParameter는 String으로 받아오기때문에 숫자는 숫자형자료형으로 형변환을 해줘야 한다
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post요청옴!!");
		request.setCharacterEncoding("UTF-8"); // 리퀘스트 객체의 셋 파라미터객체를 utf8로 설정해주어야 글자가 안깨진다 (post요청일때만)
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("phone"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("addresss"));
		System.out.println(request.getParameter("pw"));
		
		response.setContentType("text/prain; charset=utf8");
//		String name = request.getParameter("name");
//		String phone = request.getParameter("phone");
//		String email = request.getParameter("email");
//		String addresss = request.getParameter("addresss");
//		String pw = request.getParameter("pw");
		
		
//		response.getWriter().print("name : " + name);
//		response.getWriter().print(phone);
//		response.getWriter().print(email);
//		response.getWriter().print(addresss);
//		response.getWriter().print(pw);
		
		PrintWriter out = response.getWriter();
		
		out.println("name : " + request.getParameter("name"));
		out.println("phone : " + request.getParameter("phone"));
		out.println("email : " + request.getParameter("email"));
		out.println("addresss : " + request.getParameter("addresss"));
		out.println("pw : " + request.getParameter("pw"));
	}

}
