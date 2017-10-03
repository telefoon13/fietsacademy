package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VerwijderServlet", urlPatterns = "/docenten/verwijderen.htm")
public class VerwijderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final transient DocentService docentService = new DocentService();
	private static final String REDIRECT_URL = "%s/docenten/zoeken.htm";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		docentService.delete(Long.parseLong(request.getParameter("id")));


		response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
