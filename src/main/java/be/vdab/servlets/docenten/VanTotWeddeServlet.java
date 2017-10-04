package be.vdab.servlets.docenten;

import be.vdab.entities.DocentenEntity;
import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "VanTotWeddeServlet", urlPatterns = "/docenten/vantotwedde.htm")
public class VanTotWeddeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final transient DocentService docentService = new DocentService();
	private static final String VIEW = "/WEB-INF/JSP/docenten/vantotwedde.jsp";
	private static final int AANTAL_RIJEN = 20;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getQueryString() == null) {
			request.setAttribute("tot",docentService.findMaxWedde());
		} else {
			Map<String, String> fouten = new HashMap<>();
			try {
				BigDecimal van = new BigDecimal(request.getParameter("van"));
				try {
					BigDecimal tot = new BigDecimal(request.getParameter("tot"));
					int vanafRij = request.getParameter("vanafRij") == null ? 0 :
							Integer.parseInt(request.getParameter("vanafRij"));
					request.setAttribute("vanafRij", vanafRij);
					request.setAttribute("aantalRijen", AANTAL_RIJEN);
					List<DocentenEntity> docenten =
							docentService.findByWeddeBetween(van, tot, vanafRij, AANTAL_RIJEN + 1);
					if (docenten.size() <= AANTAL_RIJEN) {
						request.setAttribute("laatstePagina", true);
					} else {
						docenten.remove(AANTAL_RIJEN);
					}
					request.setAttribute("docenten", docenten);
				} catch (NumberFormatException ex) {
					fouten.put("tot", "tik een getal");
				}
			} catch (NumberFormatException ex) {
				fouten.put("van", "tik een getal");
			}
			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
