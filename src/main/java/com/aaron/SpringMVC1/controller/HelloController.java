package com.aaron.SpringMVC1.controller;

import javax.servlet.http.*;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.aaron.SpringMVC1.service.HelloService;

/**
 *
 * @author agabriel
 */
public class HelloController extends SimpleFormController {
    
	private HelloService helloService;
	
    public HelloController() {
        //Initialize controller properties here or 
        //in the Web Application Context

    	setCommandClass(Name.class);
        setCommandName("name");
        setSuccessView("helloView");
        setFormView("nameView");
    }
    
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
    


    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
     @Override
     protected ModelAndView onSubmit(
     HttpServletRequest request, 
     HttpServletResponse response, 
     Object command, 
     BindException errors) throws Exception {
    	 Name name = (Name) command;
         ModelAndView mv = new ModelAndView(getSuccessView());
         mv.addObject("helloMessage", helloService.sayHello(name.getValue()));
         return mv;
     }
     
}
