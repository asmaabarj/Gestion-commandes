package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import gestion_commande.models.Produit;
import gestion_commande.services.ProduitServices;

public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProduitServices produitser;
	private TemplateEngine templateEngine;

	public ProduitServlet() {
		super();
		produitser = new ProduitServices();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");

		templateEngine = new TemplateEngine();
		templateEngine.addTemplateResolver(templateResolver);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageStr = request.getParameter("page");
		int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
		int pageSize = 5;

		List<Produit> produits = produitser.getPage(page, pageSize);
		long totalProduits = produitser.countProduit();
		int totalPages = (int) Math.ceil((double) totalProduits / pageSize);

		WebContext context = new WebContext(request, response, getServletContext());
		context.setVariable("produits", produits);
		context.setVariable("currentPage", page);
		context.setVariable("totalPages", totalPages);

		response.setContentType("text/html;charset=UTF-8");
		templateEngine.process("produit", context, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
