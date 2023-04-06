package com.study.servlet.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebFilter("/*") // *을 주면 모든 요청들은 다 filter을 거친다
// 인증관련
public class AuthFilter extends HttpFilter implements Filter {

	public AuthFilter() {

	}

	public void destroy() {
//		객체가 소멸될 때 한번 실행 

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 전처리
		System.out.println("전처리");
		/*
		 * 1. url: role요청이 들어왔을 때 로그인이 되어있는지 확인 
		 * 2. 로그인이 되어 있으면 doFilter를 통해 서블릿에 접근허용
		 * 3. 로그인이 되어있지 않으면 response를 통해 로그인 페이지로 이동 
		 */
		
		// request가 HttpServletRequest으로 들어왔기에 ServletRequest으로 업캐스팅해서 들어옴
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; // 그래서 다운캐스팅 해야함
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		
//		httpRequest.getSession().setMaxInactiveInterval(60*60*24*365); // 1분, 1시간, 하루, 1년
		
		List<String> antMatchers = new ArrayList<>();
		antMatchers.add("/user");
		antMatchers.add("/mypage");
		
		for(String antMatcher : antMatchers) {
			if(uri.startsWith(antMatcher)) {
				// if > antMatcher에 등록된 URI로 시작하면 인증을 거친다.
				HttpSession session = httpRequest.getSession();
				if(session.getAttribute("AuthenticpatiorPrincipal") == null) {
					httpResponse.sendRedirect("/login.html");
					return;
				}
			}
		}
		
		
		chain.doFilter(request, response); // 다음(chain)으로 실행될 filter나 servlet
		System.out.println("후처리");
		// 후처리
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// 객체가 생성될 때 딱 한번 실행

	}

}
