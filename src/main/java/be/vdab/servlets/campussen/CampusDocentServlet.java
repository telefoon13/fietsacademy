package be.vdab.servlets.campussen;

import be.vdab.services.CampusService;
import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CampusDocentServlet", urlPatterns = "/campussen/docenten.htm")
public class CampusDocentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/docenten.jsp";
	private final transient CampusService campusService = new CampusService();
	private final transient DocentService docentService = new DocentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("campussen", campusService.findAll());
		String id = request.getParameter("id");

		if (request.getParameter("bestbetaalde") != null){
			request.setAttribute("docenten", docentService.findBestBetaaldeVanEenCampus(Long.parseLong(id)));
		}

		if (id != null){
			campusService.read(Long.parseLong(id)).ifPresent(campussenEntity -> request.setAttribute("campus",campussenEntity));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
