package com.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Cart entity
@Entity
public class Cart {
 @Id
 private long id;
 
 private String key;// Unique cart key

 private Long userId;

 private Date date;

 @OneToMany( cascade = CascadeType.ALL)
 private List<LineItem> lineItems;

 private double totalPrice;

 private String currency;

 private CartStatus cartStatus;

public Cart() {
	super();
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}



public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}



public List<LineItem> getLineItems() {
	return lineItems;
}

public void setLineItems(List<LineItem> lineItems) {
	this.lineItems = lineItems;
}

public double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}

public String getCurrency() {
	return currency;
}

public void setCurrency(String currency) {
	this.currency = currency;
}

public CartStatus getCartStatus() {
	return cartStatus;
}

public void setCartStatus(CartStatus cartStatus) {
	this.cartStatus = cartStatus;
}






 // Constructors, getters, setters
}
