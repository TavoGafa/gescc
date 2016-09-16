package co.com.ges.seguridad;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import co.com.ges.controller.EmpresaControl;
import co.com.ges.controller.LoginControl;

@SuppressWarnings(value = { "all" })
public class FiltroCheck implements Filter{
	private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=filterConfig;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequest)request).getSession().getAttribute(LoginControl.AUTH_KEY)==null){
			if (((HttpServletRequest)request).getSession().getAttribute(EmpresaControl.AUTH_KEY)==null){
				((HttpServletResponse)response).sendRedirect("../login/login.faces");
			}else{
				chain.doFilter(request, response);
			}
			
		}else{
			chain.doFilter(request, response);
		}	
	}

	@Override
	public void destroy() {
		config=null;
		
	}

}
