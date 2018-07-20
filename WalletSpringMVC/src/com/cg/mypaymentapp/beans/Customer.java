package com.cg.mypaymentapp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer {

	@Id
	@NotEmpty
	@Column(name="mobile_number")
	private String mobileNo;
	@Column(name="name")
	@NotEmpty
	private String name;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;

	

	public Customer() {
		super();
	}

	public Customer(String name2, String mobileNo2, Wallet wallet2) {
		this.name = name2;
		this.mobileNo = mobileNo2;
		this.wallet = wallet2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	

	@Override
	public String toString() {
		return "Customer name=" + name + ", mobileNo=" + mobileNo + wallet;
	}

}
