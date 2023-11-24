package com.demo.dto;

import com.demo.entity.Cart;
import com.demo.entity.OrderStatus;

public class OrderDTO {
	private Long id;
    private Cart cart;
    private double totalPrice;
    private String paymentStatus;
    private OrderStatus orderStatus;
   
	public OrderDTO() {
		super();
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
	




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public OrderStatus getOrderStatus() {
		return orderStatus;
	}




	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}




	public Long getPayment() {
		// TODO Auto-generated method stub
		return null;
	}
	

    
    // Constructors, getters, setters
}