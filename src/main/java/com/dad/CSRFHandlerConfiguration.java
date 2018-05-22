package com.dad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
}

class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,final Object handler, final ModelAndView modelAndView) throws Exception {
		//if request.getRequestURI() gs-blablabla no hacer esto
		// opción 2: no hacer esto automáticamente, añadir esto a cada método del controlador
		//str1.toLowerCase().contains(str2.toLowerCase())
		/*
		String myUri = request.getRequestURI(); // /bla-bla-bla
		String myUrl = request.getRequestURL().toString(); // https://localhost:8443/bla-bla-bla
		System.out.println("myUri ["+myUri+"]");
		System.out.println("myUrl ["+myUrl+"]");
		
		String myAuthType = request.getAuthType();
		String myContentType = request.getContentType();
		String myContextPath = request.getContextPath();
		
		System.out.println("myAuthType ["+myAuthType+"]");
		System.out.println("myContentType ["+myContentType+"]");
		System.out.println("myContextPath ["+myContextPath+"]");	
		String myMethod = request.getMethod();
		System.out.println("myMethod ["+myMethod+"]");
		*/
		
		if ((!request.getRequestURI().contains("search")) && (!request.getRequestURI().contains("resultados"))
				&& (!request.getRequestURI().contains("busqueda"))
				&& (!request.getRequestURI().contains("sitters"))
				&& (!request.getRequestURI().contains("cache"))
				&& (!request.getRequestURI().contains("advert"))
				){
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
	    	modelAndView.addObject("token", token.getToken());
		}
  
		 	
    }
}