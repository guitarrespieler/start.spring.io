package hu.bme.iit.messenger.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/login.html");
		registry.addViewController(LoginController.loginPage).setViewName("redirect:/login.html");
		registry.addViewController(RegController.regPage).setViewName("redirect:/registration.html");
		registry.addViewController(ConversationsController.conversationsPage).setViewName("redirect:/conversations.html");
	}
}
