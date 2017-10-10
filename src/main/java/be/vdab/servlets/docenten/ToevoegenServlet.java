package be.vdab.servlets.docenten;

import be.vdab.entities.DocentenEntity;
import be.vdab.enums.Geslacht;
import be.vdab.exceptions.DocentBestaadAlException;
import be.vdab.services.CampusService;
import be.vdab.services.DocentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "ToevoegenServlet", urlPatterns = "/docenten/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/docenten/zoeken.htm?id=%d";
	private final transient DocentService docentService = new DocentService();
	private final transient CampusService campusService = new CampusService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();

		String voornaam = request.getParameter("voornaam");
		if (!DocentenEntity.isVoornaamValid(voornaam)) {
			fouten.put("voornaam", "verplicht");
		}

		String familienaam = request.getParameter("familienaam");
		if (!DocentenEntity.isFamilienaamValid(familienaam)) {
			fouten.put("familienaam", "verplicht");
		}

		BigDecimal wedde = null;
		try {
			wedde = new BigDecimal(request.getParameter("wedde"));
			if (!DocentenEntity.isWeddeValid(wedde)) {
				fouten.put("wedde", "tik een positief getal of 0");
			}
		} catch (NumberFormatException ex) {
			fouten.put("wedde", "tik een positief getal of 0");
		}

		String geslacht = request.getParameter("geslacht");
		if (geslacht == null) {
			fouten.put("geslacht", "verplicht");
		}

		long rijksRegisterNr = 0;
		try {
			rijksRegisterNr = Long.parseLong(request.getParameter("rijksregisternr"));
			if (!DocentenEntity.isRijksRegisterNrValid(rijksRegisterNr)) {
				fouten.put("rijksregisternr", "verkeerde cijfers");
			}
		} catch (NumberFormatException ex) {
			fouten.put("rijksregisternr", "verkeerde cijfers");
		}

		String campusId = request.getParameter("campussen");
		if (campusId == null){
			fouten.put("campussen","Verplicht");
		}

		if (fouten.isEmpty()) {
			DocentenEntity docent = new DocentenEntity(voornaam, familienaam, wedde, Geslacht.valueOf(geslacht), rijksRegisterNr);
			campusService.read(Long.parseLong(campusId)).ifPresent(campussenEntity -> docent.setCampus(campussenEntity));
			try{
				docentService.create(docent);
				response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(), docent.getId())));
			}catch (DocentBestaadAlException ex){
				fouten.put("rijksregisternr", "Bestaad al");
			}
		}
		if (!fouten.isEmpty()){
			request.setAttribute("fouten", fouten);
			request.setAttribute("campussen", campusService.findAll());
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("campussen", campusService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}
