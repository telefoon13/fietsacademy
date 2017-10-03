package be.vdab.filters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.htm")
public class JPAFilter implements Filter {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("fietsacademy");
	private static final ThreadLocal<EntityManager> entityManagers = new ThreadLocal<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
// geen code nodig hier
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		entityManagers.set(entityManagerFactory.createEntityManager());
		try{
			request.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		} finally {
			EntityManager entityManager = entityManagers.get();
			entityManager.close();
			entityManagers.remove();
		}

	}

	public static EntityManager getEntityManager(){
		return entityManagers.get();
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}
}