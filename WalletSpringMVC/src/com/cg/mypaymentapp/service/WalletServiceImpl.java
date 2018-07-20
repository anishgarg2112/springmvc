package com.cg.mypaymentapp.service;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;


@Component(value="walletsrv")
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo repo;

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer createAccount(Customer customer) {
		
		return repo.save(customer); 
	}
	@Override
	public Customer showBalance(String mobileNo) {
		Customer customer = repo.findOne(mobileNo);
		
		if (customer != null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}
	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		Customer customerSrc = withdrawAmount(sourceMobileNo, amount);
		Customer customerTgt = depositAmount(targetMobileNo, amount);
		if (customerSrc != null && customerTgt != null)
			return customerSrc;
		else
			return null;
	}
	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		if (customer != null) {
			BigDecimal currBalance = customer.getWallet().getBalance();
			BigDecimal finalBalance = currBalance.add(amount);
			Wallet wallet = customer.getWallet();
			wallet.setBalance(finalBalance);
			customer.setWallet(wallet);

			repo.save(customer);
			return customer;
		} else
			throw new InvalidInputException("Invalid mobile no ");

	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		if (customer != null) {
			BigDecimal currBalance = customer.getWallet().getBalance();
			int compare = currBalance.compareTo(amount);
			if (compare == -1) {
				throw new InsufficientBalanceException("Insufficient Balance to complete the transaction");
			} else {
				BigDecimal finalBalance = currBalance.subtract(amount);
				Wallet wallet = customer.getWallet();
				wallet.setBalance(finalBalance);
				customer.setWallet(wallet);

				repo.save(customer);
				return customer;
			}
		} else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public boolean isValid(Customer customer) {
		String regexName = "[a-zA-Z]*";
		String regexNum = "[1-9][0-9]{9}";
		if (customer.getName().equals(null) || customer.getMobileNo().equals(null)
				|| customer.getWallet().getBalance().equals(null))
			throw new NullPointerException();
		if (customer.getName().matches(regexName) && customer.getMobileNo().matches(regexNum))
			return true;
		return false;
	}
}