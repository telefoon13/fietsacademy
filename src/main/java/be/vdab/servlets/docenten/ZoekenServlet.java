package be.vdab.servlets.docenten;

import be.vdab.repositories.DocentRepository;
import be.vdab.services.DocentService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(name = "ZoekenServlet", urlPatterns = "/docenten/zoeken.htm")
public class ZoekenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
	private final transient DocentService docentService = new DocentService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getQueryString() != null){
			try{
				docentService.read(Long.parseLong(request.getParameter("id"))).ifPresent(docent -> request.setAttribute("docent", docent));
			} catch (NumberFormatException ex){
				request.setAttribute("fouten", Collections.singletonMap("id", "Tik een getal"));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request,response);
	}
}
