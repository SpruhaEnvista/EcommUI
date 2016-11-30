/**
 * 
 */
package com.envista.msi.api.rest;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;

import com.envista.msi.api.MsiApp;
import com.envista.msi.api.config.MSIClientConfiguration;

/**
 * @author SANKER
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MsiApp.class, MSIClientConfiguration.class})
@WebAppConfiguration
@IntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class WebappTestEnvironment extends AbstractApiIntegrationTests {
	
	protected final Logger logger = LoggerFactory.getLogger(WebappTestEnvironment.class);
		
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private MockMvc mockMvc;
	
	@Value("${userInTest.username}")
	private String username;
	
	@Value("${userInTest.password}")
	private String password;
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockRestMvc;

	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	@Before
	public void setupMockMvc() throws NamingException {
	    // setup mock MVC
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(defaultSetup()).addFilter(springSecurityFilterChain).build();
		this.mockRestMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
	}
	
	/**
	 * @return the mockMvc
	 */
	protected MockMvc mockRestMvc() {
		return mockMvc;
	}
		
	private MockMvcConfigurer defaultSetup() { 
        return new MockMvcConfigurer() { 
           
			@Override
			public void afterConfigurerAdded(ConfigurableMockMvcBuilder<?> builder) {
				//configurableMockMvcBuilder.alwaysExpect(status().isOk());
			}
			 @Override 
	            public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> configurableMockMvcBuilder, WebApplicationContext webApplicationContext) { 
	             /*   return new RequestPostProcessor() { 
	                  	@Override
						public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest) {
							mockHttpServletRequest.setAttribute("security", "true"); 
	                        return mockHttpServletRequest; 
						} 
	                }; */
				  return bearerToken(username, password);
	            }
        }; 
    } 
	
	/* @Test
	    public void verifyWac() {
	        ServletContext servletContext = wac.getServletContext();
	        Assert.assertNotNull(servletContext);
	        Assert.assertTrue(servletContext instanceof MockServletContext);
	        for (String beanName : wac.getBeanDefinitionNames()) {
	            if (beanName.contains("springCustomContextConfigurationExample")) {
	                log.debug("Bean Name: " + beanName);
	                log.debug("Bean " + wac.getBean(beanName));
	            }
	        }
	    }*/
	 
	
	/**
	 * @return the mockMvc
	 */
	protected MockMvc mockRestMvc(boolean security) {
		if(security)
			return mockMvc;
		return mockRestMvc;
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
