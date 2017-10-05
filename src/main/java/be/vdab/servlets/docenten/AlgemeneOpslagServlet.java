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

@WebServlet(name = "AlgemeneOpslagServlet", urlPatterns = "/docenten/algemeneopslag.htm")
public class AlgemeneOpslagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final transient DocentService docentService = new DocentService();
	private static final String VIEW = "/WEB-INF/JSP/docenten/algemeneopslag.jsp";
	private static final String REDIRECT_URL = "%s/docenten/aantalperwedde.htm";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();
		try{
			BigDecimal percentage = new BigDecimal(request.getParameter("percentage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0){
				fouten.put("percentage", "0 of groter aub.");
			} else {
				docentService.algemeneOpslag(percentage);
			}
		} catch (NumberFormatException ex){
			fouten.put("percentage", "0 of groter aub.");
		}

		if (fouten.isEmpty()){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}
