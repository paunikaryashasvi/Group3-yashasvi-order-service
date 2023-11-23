package com.demo.dto;

public class LineItemDTO {
    private String productKey;
    private String variantKey;
    private double price;
    private int quantity;
    private double totalPrice;
	public LineItemDTO() {
		super();
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getVariantKey() {
		return variantKey;
	}
	public void setVariantKey(String variantKey) {
		this.variantKey = variantKey;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
	
    

    // Constructors, getters, setters
}