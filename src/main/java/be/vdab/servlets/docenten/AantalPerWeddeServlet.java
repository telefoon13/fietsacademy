package be.vdab.servlets.docenten;

import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AantalPerWeddeServlet", urlPatterns = "/docenten/aantalperwedde.htm")
public class AantalPerWeddeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/aantalperwedde.jsp";
	private final transient DocentService docentService = new DocentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("weddesEnAantallen",docentService.findAantalDocentenPerWedde());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
