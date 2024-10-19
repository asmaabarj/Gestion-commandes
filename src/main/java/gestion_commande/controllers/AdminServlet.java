package gestion_commande.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import gestion_commande.models.Admin;
import gestion_commande.services.AdminService;
import gestion_commande.utilis.PasswordUtil;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminService adminServices;
    private TemplateEngine templateEngine;

    public AdminServlet() {
        super();
        adminServices = new AdminService();
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
        String searchQuery = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
        int pageSize = 4;
        List<Admin> admins = null;
        int totalPages = 0;

        List<Admin> allAdmins = adminServices.getAll(); 

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            admins = allAdmins.stream()
                .filter(admin -> admin.getNom().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        } else {
            admins = allAdmins; 
        }

        long totalAdmins = admins.size();
        totalPages = (int) Math.ceil((double) totalAdmins / pageSize);
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, admins.size());
        List<Admin> pagedAdmins = admins.subList(fromIndex, toIndex);

        WebContext context = new WebContext(request, response, getServletContext(), request.getLocale());
        context.setVariable("admins", pagedAdmins);
        context.setVariable("currentPage", page);
        context.setVariable("totalPages", totalPages);
        context.setVariable("searchQuery", searchQuery); 
		response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("admins", context, response.getWriter());
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String motpasse = request.getParameter("motpasse");
            String niveauaccesStr = request.getParameter("niveauacces");
            Integer niveauacces = Integer.parseInt(niveauaccesStr);
            
            Admin newAdmin = new Admin();
            newAdmin.setNom(nom);
            newAdmin.setPrenom(prenom);
            newAdmin.setEmail(email);
            newAdmin.setMotDePasse(motpasse);
            newAdmin.setNiveauAcces(niveauacces);

            adminServices.create(newAdmin); 
            response.sendRedirect(request.getContextPath() + "/admins");

        } else if ("delete".equals(action)) {
            // Delete logic (unchanged)
            Long id = Long.parseLong(request.getParameter("adminId"));
            adminServices.delete(id);
            response.sendRedirect(request.getContextPath() + "/admins");

        } else if ("edit".equals(action)) {
            // Edit logic
            Long id = Long.parseLong(request.getParameter("adminId"));
            Optional<Admin> optionalAdmin = adminServices.findById(id);
            
            if (optionalAdmin.isPresent()) {
                Admin admin = optionalAdmin.get();

                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("email");
                String niveauaccesStr = request.getParameter("niveauacces");
                Integer niveauacces = Integer.parseInt(niveauaccesStr);

                admin.setNom(nom);
                admin.setPrenom(prenom);
                admin.setEmail(email);
                admin.setNiveauAcces(niveauacces);

                String motpasse = request.getParameter("motpasse");
                if (motpasse != null && !motpasse.isEmpty()) {
                    // Hash the new password and update
                    String hashedPassword = PasswordUtil.hashPassword(motpasse);
                    admin.setMotDePasse(hashedPassword);
                }

                adminServices.update(admin);
            }

            response.sendRedirect(request.getContextPath() + "/admins");
        }
    }


}

