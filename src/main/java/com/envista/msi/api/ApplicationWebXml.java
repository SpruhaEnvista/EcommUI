package com.envista.msi.api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * @author SANKER
 *
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

    /* (non-Javadoc)
     * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        MsiApp.addDefaultProfile(application.application());
        return application.sources(MsiApp.class);
    }
 }
