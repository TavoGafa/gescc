package co.com.ges.seguridad;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestLoggingFilter
 */
@WebServlet("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
       
	private ServletContext context;
	  
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RequestLoggingFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	    this.context.log("RequestLoggingFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest)request;
		    Enumeration<String> params = req.getParameterNames();
		    while (params.hasMoreElements())
		    {
		      String name = (String)params.nextElement();
		      String value = request.getParameter(name);
		      this.context.log(req.getRemoteAddr() + "::Request Params::{" + name + "=" + value + "}");
		    }
		    Cookie[] cookies = req.getCookies();
		    if (cookies != null) {
		      for (Cookie cookie : cookies) {
		        this.context.log(req.getRemoteAddr() + "::Cookie::{" + cookie.getName() + "," + cookie.getValue() + "}");
		      }
		    }
		    chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
