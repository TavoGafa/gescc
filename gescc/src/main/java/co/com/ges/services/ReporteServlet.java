package co.com.ges.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class ReporteServlet
 */
@WebServlet("/ReporteServlet")
public class ReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReporteServlet() {
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
		try {
			String id_arg = request.getParameter("codigo");
			int serial = Integer.parseInt(id_arg);
			System.out.println(serial);
			Map parameters = new HashMap();
			File reportFile = new File(getServletContext().getRealPath("reportes//haber.jasper"));
			parameters.put("identificador", Integer.valueOf(serial));
			byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
