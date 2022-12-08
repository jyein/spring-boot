package com.study.fliter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.domain.User;

@WebFilter("/*")
public class SercurityFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String requestURI = req.getRequestURI();
		String antMatchers = "/mapage, /mypage/password";
		String antMatchers2 = "/login, /register";
		String logoutURI = "/logout";
		String adminPage = "/admin";
		
		System.out.println("시큘리티 동작!");

		if (antMatchers.contains(requestURI) && !authorization(req.getSession())) {
			// antMatchers가 requestURI를 포함하고 있는지? 그리고 req.getSession을 날려줫을때 인증이 되지않으면
			// ture (로그인을 안했다는 의미로 둘중하나에 들어왓다)
			resp.sendRedirect("/login");
			return;

		}

		if (antMatchers2.contains(requestURI) && authorization(req.getSession())) {
			resp.sendRedirect("/mypage");
			return;
		}

		if (logoutURI.equalsIgnoreCase(requestURI)) {
			req.getSession().invalidate();
			resp.sendRedirect("/login");
			return;
		}
		
		if(requestURI.contains(adminPage) && !hasRole(req.getSession(), "ADMIN")) {
			resp.sendError(403, "Forbidden"); // 403은 권한이 없을때 뜨는 에러코드
//			resp.sendRedirect("/error/role");
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean authorization(HttpSession session) {
		User principalUser = (User) session.getAttribute("principal");
		System.out.println(principalUser);
		return principalUser != null;
	}
	
	private boolean hasRole(HttpSession session, String role) {
		
		AtomicBoolean result = new AtomicBoolean(false);
		
		if(authorization(session)) {
			User principalUser = (User) session.getAttribute("principal");
			
			String[] roleArray = principalUser.getRoles().replaceAll(" ", "").split(",");
			List<String> roleList = Arrays.asList(roleArray);
			System.out.println(roleList);
			roleList.forEach(r -> {
				if(r.startsWith("ROLE_")/* startsWith("") : ""로 시작하는지 찾는다 시작하면 true 아니면 false */ && r.contains(role)) {
					result.set(true);
				}
			});			
		}	
		return result.get();
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
