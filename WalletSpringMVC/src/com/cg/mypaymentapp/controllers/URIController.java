package com.cg.mypaymentapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.mypaymentapp.beans.Customer;

@Controller
public class URIController {
	@RequestMapping(value="/")
	public String getIndexPage()
	{
		return "index";
	}
	@RequestMapping(value="/Registration")
	public String getRegistrationPage()
	{
		return "registration";
	}
	@RequestMapping(value="/Login")
	public String getLoginPage()
	{
		return "Login";
	}
	
	@ModelAttribute("customer")
	public Customer getCustomer(){
		return new Customer();
		
	}
}
