package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;

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
		JavaxServletWebApplication webApplication = JavaxServletWebApplication.buildApplication(getServletContext());
		ITemplateResolver templateResolver = new WebApplicationTemplateResolver(webApplication);
		((WebApplicationTemplateResolver) templateResolver).setTemplateMode(TemplateMode.HTML);
		((WebApplicationTemplateResolver) templateResolver).setPrefix("/WEB-INF/templates/");
		((WebApplicationTemplateResolver) templateResolver).setSuffix(".html");
		
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Produit> produits = produitser.getAll();
		
		JavaxServletWebApplication webApplication = JavaxServletWebApplication.buildApplication(getServletContext());
		WebContext context = new WebContext(webApplication.buildExchange(request, response));
		context.setVariable("produits", produits);
		
		response.setContentType("text/html;charset=UTF-8");
		templateEngine.process("produit", context, response.getWriter());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
