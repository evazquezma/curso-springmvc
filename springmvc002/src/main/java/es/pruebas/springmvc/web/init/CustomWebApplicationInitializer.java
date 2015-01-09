package es.pruebas.springmvc.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;


public class CustomWebApplicationInitializer implements WebApplicationInitializer {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	
	
    @Override
    public void onStartup(ServletContext sc) {
    	logger.info("Registrando servlet");
    	
        ServletRegistration.Dynamic registration = sc.addServlet("spring", new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        
               
        sc.addListener(org.springframework.web.context.ContextLoaderListener.class);
    }

}
