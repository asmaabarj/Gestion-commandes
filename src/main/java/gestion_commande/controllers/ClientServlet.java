package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import gestion_commande.models.Client;
import gestion_commande.services.ClientService;
import gestion_commande.utilis.PasswordUtil;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class ClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClientService clientServices;
    private TemplateEngine templateEngine;

    public ClientServlet() {
        super();
        clientServices = new ClientService();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
        
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateEngine = new TemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		templateEngine.addTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("admin") == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }
	    
        String searchQuery = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        int pageSize = 4;
        List<Client> clients = null;
        int totalPages = 0;

        List<Client> allClients = clientServices.getAll(); 

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            clients = allClients.stream()
                .filter(client -> client.getNom().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            clients = allClients; 
        }

        long totalClients = clients.size();
        totalPages = (int) Math.ceil((double) totalClients / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, clients.size());
        List<Client> pagedClients = clients.subList(fromIndex, toIndex);

        WebContext context = new WebContext(request, response, getServletContext(), request.getLocale());
        context.setVariable("clients", pagedClients);
        context.setVariable("currentPage", page);
        context.setVariable("totalPages", totalPages);
        context.setVariable("searchQuery", searchQuery); 
		response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("clients", context, response.getWriter());
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String motpasse = request.getParameter("motpasse");
            String adresseLivraison = request.getParameter("adresselivraison");
            String moyenPaiement = request.getParameter("moyenpaiment");

            Client newClient = new Client();
            newClient.setNom(nom);
            newClient.setPrenom(prenom);
            newClient.setEmail(email);
            newClient.setMotDePasse(motpasse);
            newClient.setAdresseLivraison(adresseLivraison);
            newClient.setMoyenPaiement(moyenPaiement);

            clientServices.create(newClient); 
            response.sendRedirect(request.getContextPath() + "/clients");
           
        }else if ("delete".equals(action)) {
        	Long id = (long) Integer.parseInt(request.getParameter("clientId"));
        	clientServices.delete(id);
            response.sendRedirect(request.getContextPath() +"/clients");
        }else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("clientId"));
            Optional<Client> optionalClient = clientServices.findById(id);
            
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();

                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("email");
                String adresselivraison = request.getParameter("adresselivraison");
                String moyenpaiement = request.getParameter("moyenpaiement");

                client.setNom(nom);
                client.setPrenom(prenom);
                client.setEmail(email);
                client.setAdresseLivraison(adresselivraison);
                client.setMoyenPaiement(moyenpaiement);


                String motpasse = request.getParameter("motpasse");
                if (motpasse != null && !motpasse.isEmpty()) {
                    String hashedPassword = PasswordUtil.hashPassword(motpasse);
                    client.setMotDePasse(hashedPassword);
                }

                clientServices.update(client);
            }

            response.sendRedirect(request.getContextPath() + "/clients");
        }
    }


}

