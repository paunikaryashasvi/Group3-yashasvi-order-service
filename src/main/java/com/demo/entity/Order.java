package com.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

//Order entity
@Entity
public class Order {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn
 private Cart cart;

 private double totalPrice;

 private String paymentStatus;

 private OrderStatus orderStatus;

public Order() {
	super();
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}



public Cart getCart() {
	return cart;
}

public void setCart(Cart cart) {
	this.cart = cart;
}

public double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}

public String getPaymentStatus() {
	return paymentStatus;
}

public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}

public OrderStatus getOrderStatus() {
	return orderStatus;
}

public void setOrderStatus(OrderStatus orderStatus) {
	this.orderStatus = orderStatus;
}



 // Constructors, getters, setters
}



