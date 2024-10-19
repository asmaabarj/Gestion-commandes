package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import gestion_commande.models.Produit;
import gestion_commande.services.ProduitServices;
import gestion_commande.utilis.TemplateEngineUtil;

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
	    templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("admin") == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }
		String pageStr = request.getParameter("page");
		int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
		int pageSize = 5;
		List<Produit> produits=null;
		int totalPages=0;
		String searchQuery = request.getParameter("search");

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		if (searchQuery != null && !searchQuery.isEmpty()) {
			 produits = produitser.getAll(searchQuery);
			 totalPages = produits.size();
		} else {
			 produits = produitser.getPage(page, pageSize);
				long totalProduits = produitser.countProduit();
				 totalPages = (int) Math.ceil((double) totalProduits / pageSize);

		}
		
		

		WebContext context = new WebContext(request, response, getServletContext());
		context.setVariable("produits", produits);
		context.setVariable("currentPage", page);
		context.setVariable("totalPages", totalPages);

		response.setContentType("text/html;charset=UTF-8");
		templateEngine.process("produit", context, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
				case "create":
					createProduct(request, response);
					break;
				case "update":
					updateProduct(request, response);
					break;
				case "delete":
					deleteProduct(request, response);
					break;
				default:
					doGet(request, response);
			}
		} else {
			doGet(request, response);
		}
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		double prix = Double.parseDouble(request.getParameter("prix"));
		int stock = Integer.parseInt(request.getParameter("stock"));

	    if (description.isEmpty() ||nom.isEmpty()) {
            request.setAttribute("errorMessage", "La champ du input est obligatoire.");
            doGet(request, response);
            return;
        }
        if(prix<0 || stock<0){
            request.setAttribute("errorMessage", "La champ du input >=0 est obligatoire.");
            doGet(request, response);
            return;
        }
        
		Produit newProduct = new Produit();
		newProduct.setNom(nom);
		newProduct.setDescription(description);
		newProduct.setPrix(prix);
		newProduct.setStock(stock);


		 produitser.createProduit(newProduct);
		 
	 response.sendRedirect(request.getContextPath() + "/ProduitServlet");
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        Long id = Long.parseLong(request.getParameter("id"));
	        String nom = request.getParameter("nom");
	        String description = request.getParameter("description");
	        double prix = Double.parseDouble(request.getParameter("prix"));
	        int stock = Integer.parseInt(request.getParameter("stock"));

	        if (description.isEmpty() ||nom.isEmpty()) {
	            request.setAttribute("errorMessage", "La champ du input est obligatoire.");
	            doGet(request, response);
	            return;
	        }
	        if(prix<0 || stock<0){
	            request.setAttribute("errorMessage", "La champ du input >=0 est obligatoire.");
	            doGet(request, response);
	            return;
	        }

	        Produit updatedProduct = new Produit();
	        updatedProduct.setId(id);
	        updatedProduct.setNom(nom);
	        updatedProduct.setDescription(description);
	        updatedProduct.setPrix(prix);
	        updatedProduct.setStock(stock);

	        produitser.updateProduit(updatedProduct);
	        response.sendRedirect(request.getContextPath() + "/ProduitServlet");

	    } catch (NumberFormatException e) {
	        request.setAttribute("errorMessage", "Format de données incorrect. Veuillez réessayer.");
	        doGet(request, response);
	    }
	}


	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = (long) Integer.parseInt(request.getParameter("id"));
	produitser.deleteProduit(id);

		response.sendRedirect(request.getContextPath() + "/ProduitServlet");
	}
}
