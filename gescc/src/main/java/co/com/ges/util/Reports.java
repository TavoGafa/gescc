package co.com.ges.util;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Reports implements Serializable {
	private static final long serialVersionUID = 1L;
	private String etiquetaDesde = "REPORTE";
	private String etiquetaHasta = "HISTORIAL";

	public void crearReporte(int tipRep, String rep) {
		try {
			DataSource ds = (DataSource) InitialContext.doLookup("java:jboss/env/jdbc/ges");
			Connection conn = ds.getConnection();

			Map args = new HashMap();
			args.put("case", rep);
			String path = "C:\\sqljdbc_4.0\\enu\\HistoryReport.jasper";
			JasperPrint reporte = JasperFillManager.fillReport(path, args, conn);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();

			String archivo = this.etiquetaDesde + "_" + this.etiquetaHasta + ".pdf";
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + archivo);
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(reporte, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
