package com.demo.dto;

import java.sql.Date;
import java.util.List;

import com.demo.entity.CartStatus;
import com.demo.entity.LineItem;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Size;

public class CartDTO {
	private Long id;
	
	@NonNull
    private String key;
	
	@Size(min = 3)
    private Long userId;
	
    private Date date;
    private double totalPrice;
    private String currency;
    private CartStatus cartStatus;
    private List<LineItem> lineItems;
	public CartDTO() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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


	public List<LineItem> getLineItems() {
		return lineItems;
	}


	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
    
	
    

    // Constructors, getters, setters
}