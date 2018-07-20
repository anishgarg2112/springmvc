package com.cg.mypaymentapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerActionController {

	@Autowired(required = true)
	private WalletService walletService;

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {
		if (result.hasErrors())
			return new ModelAndView("registration");
		try {
			customer = walletService.createAccount(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("registrationSuccessPage", "customer", customer);
		return modelAndView;

	}

	@RequestMapping(value = "/showBalance", method = RequestMethod.POST)
	public ModelAndView showBalance( @ModelAttribute("customer") Customer customer) {
		
		try {
			customer=
					walletService.showBalance(customer.getMobileNo());
			System.out.println("hellooooooooo");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("loginSuccess", "customer", customer);
		return modelAndView;

	}

}
