package com.study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test1/page/1")
public class Test1Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/test1.html").forward(request, response);
		// 여기서 Dispatcher가 중요한 역할을 해주는데 요청을 했을때 해당 페이지로 연결을 시켜주기 때문이다. (내부에서 접근을 하고있는 것이다.)
		// 앞으로 페이지를 띄우는 요청방식을 요런식으로 할 것이다.
		// forward한다는게 doGet요청이왔을때 html파일에 요청이왔을때 request와 response를 요청을한뒤 응답까지 해주는것이다.
		
	}

}
