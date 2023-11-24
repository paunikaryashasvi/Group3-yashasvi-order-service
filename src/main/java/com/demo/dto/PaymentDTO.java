package com.demo.dto;



public class PaymentDTO {

    private Long id;
    private String transactionId;
    private double amount;
    private String paymentMethod;
    private PaymentStatus paymentStatus;

    // Constructors, getters, setters

    public PaymentDTO() {
        // Default constructor
    }

    public PaymentDTO(Long id, String transactionId, double amount, String paymentMethod, PaymentStatus paymentStatus) {
        this.id = id;
        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
