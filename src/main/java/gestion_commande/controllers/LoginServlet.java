package gestion_commande.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;

import gestion_commande.models.Admin;
import gestion_commande.models.Client;
import gestion_commande.services.AdminService;
import gestion_commande.services.ClientService;
import gestion_commande.utilis.PasswordUtil;
import gestion_commande.utilis.TemplateEngineUtil;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TemplateEngine templateEngine;
    private AdminService adminServices; 
    private ClientService clientServices;  

    public LoginServlet() {
        super();
        adminServices = new AdminService();
        clientServices = new ClientService(); 
    }

    @Override
    public void init() throws ServletException {
        super.init();
	    templateEngine = TemplateEngineUtil.getTemplateEngine(getServletContext());

        
   
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("login", new org.thymeleaf.context.WebContext(request, response, getServletContext()), response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Admin> optionalAdmin = adminServices.findByEmail(email);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            if (PasswordUtil.hashPassword(password).equals(admin.getMotDePasse())) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin); 
                response.sendRedirect(request.getContextPath() + "/admins");  
                return;
            }
        }

        Optional<Client> optionalClient = clientServices.findByEmail(email);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            if (PasswordUtil.hashPassword(password).equals(client.getMotDePasse())) {
                HttpSession session = request.getSession();
                session.setAttribute("client", client); 
                response.sendRedirect(request.getContextPath() + "/products");  
                return;
            }
        }

        request.setAttribute("error", "Email ou mot de passe incorrect");
        doGet(request, response);
    }
}
