package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import gestion_commande.models.Client;
import gestion_commande.services.ClientService;

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
        templateEngine.addTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        int pageSize = 4;
        List<Client> clients = null;
        int totalPages = 0;

        List<Client> allClients = clientServices.getAll(); // You need to implement this in your service

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
        }
      
    }


}

