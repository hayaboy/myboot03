package com.myboot03.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


//servlet-context에서 인터셉터 설정해놓음
public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	
	 @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 try {
				String viewName = getViewName(request);
				request.setAttribute("viewName", viewName);
				System.out.println("인터셉터에서 찍은 뷰네임"+viewName);
				System.out.println("----- 여기까지 인터셉터의 역할");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
	}
	 
	 private String getViewName(HttpServletRequest request) throws Exception{
			String contextPath = request.getContextPath();
			System.out.println(contextPath);
			String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
			System.out.println(uri);
			if (uri == null || uri.trim().equals("")) {
				uri = request.getRequestURI();
			}
			System.out.println("getRequestURI()통해 가져온 uri:" + uri);
			int begin = 0;
			if (!((contextPath == null) || ("".equals(contextPath)))) {
				begin = contextPath.length();
			}
			System.out.println("처음 인덱스 위치(컨텍스프 패스 길이):" +begin);
			int end;
			System.out.println(";의 위치, 없으면 -1:" + uri.indexOf(";"));
			if (uri.indexOf(";") != -1) {
				end = uri.indexOf(";");
			} else if (uri.indexOf("?") != -1) {
				end = uri.indexOf("?");
			} else {
				end = uri.length();
			}
			System.out.println("uri의 길이:"+end);
			
			String viewName = uri.substring(begin, end);
			System.out.println("최종 뷰네임"+viewName);
			
			System.out.println( "뷰네임에 .이 없으면 -1, 만약 있으면 그 위치 :  " +viewName.indexOf("."));
			if (viewName.indexOf(".") != -1) {
				viewName = viewName.substring(0, viewName.lastIndexOf("."));
				System.out.println("마지막 점앞으로 있는 부분만 가져옴 :" + viewName);
			}
			System.out.println( "뷰네임에 /이 없으면 -1, 만약 있으면 마지막 그 위치 :  " +viewName.lastIndexOf("/"));
			if (viewName.lastIndexOf("/") != -1) {
				viewName = viewName.substring(viewName.lastIndexOf("/",1), viewName.length());
				System.out.println(viewName);
			}
			return viewName;
		}
	 
	 
	 
	 
	 
	 @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
	
	}
	 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	
	} 
	 
	 
	 
	 
	 
}
