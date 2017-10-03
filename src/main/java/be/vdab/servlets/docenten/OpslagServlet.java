package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OpslagServlet", urlPatterns = "/docenten/opslag.htm")
public class OpslagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final transient DocentService docentService = new DocentService();
	private static final String REDIRECT_URL = "%s/docenten/zoeken.htm";
	private static final String VIEW = "/WEB-INF/JSP/docenten/opslag.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();

		try{
			BigDecimal percentage = new BigDecimal(request.getParameter("percantage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0){
				fouten.put("percantage", "Kies een positief getal aub.");
			} else {
				long id = Long.parseLong(request.getParameter("id"));
				docentService.opslag(id, percentage);
				response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
			}
		} catch (NumberFormatException ex){
			fouten.put("percentage", "Alleen nummers !");
		}

		if (!fouten.isEmpty()){
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}
