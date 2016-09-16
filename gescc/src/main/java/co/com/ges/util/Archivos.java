package co.com.ges.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Archivos
 */
@WebServlet("/Archivos")
public class Archivos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Archivos() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException
    	  {
    	    try
    	    {
    	      HashMap para = new HashMap();
    	      para = (HashMap)request.getSession().getAttribute("mapParameters");
    	      String arch = (String)para.get("ruta");
    	      String data = (String)para.get("datos");
    	      String nombreArchivo = (String)para.get("nombreArch");
    	      File f = new File(arch);
    	      FileInputStream in = new FileInputStream(f);
    	      byte[] lectura = new byte[in.available()];
    	      in.read(lectura);
    	      response.setContentType("text/xml");
    	      response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");
    	      response.setContentLength(lectura.length);
    	      ServletOutputStream ouputStream = response.getOutputStream();
    	      ouputStream.write(lectura);
    	      ouputStream.flush();
    	      ouputStream.close();
    	    }
    	    catch (Exception e)
    	    {
    	      e.printStackTrace();
    	    }
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
	
	public String getServletInfo()
	  {
	    return "Short description";
	  }

}
