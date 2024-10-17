package gestion_commande.utilis;

import javax.servlet.ServletContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class TemplateEngineUtil {
    private static TemplateEngine instance;

    private TemplateEngineUtil() {}

    public static synchronized TemplateEngine getTemplateEngine(ServletContext servletContext) {
        if (instance == null) {
            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
            templateResolver.setPrefix("/WEB-INF/templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");

            instance = new TemplateEngine();
            instance.addDialect(new LayoutDialect());
            instance.addTemplateResolver(templateResolver);
        }
        return instance;
    }
}
