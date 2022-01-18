package com.epam.training.epharmacy.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderNumber;
    private User client;
    private Date deliveryTime;
    private User pharmacist;
    private double totalPrice;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private List<OrderEntry> orderEntries;
    private List<Prescription> prescriptions;


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public User getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(User pharmacist) {
        this.pharmacist = pharmacist;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, client, deliveryTime, pharmacist, totalPrice, orderStatus, paymentStatus, orderEntries, prescriptions);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", client=" + client +
                ", deliveryTime=" + deliveryTime +
                ", pharmacist=" + pharmacist +
                ", amount=" + totalPrice +
                ", orderStatus=" + orderStatus +
                ", paymentStatus=" + paymentStatus +
                ", orderEntries=" + orderEntries +
                ", prescriptions=" + prescriptions +
                '}';
    }

    public static class Builder {
        Order order;

        public Builder() {
            order = new Order();
        }

        public Builder orderNumber(int orderNumber) {
            order.setOrderNumber(orderNumber);
            return this;
        }

        public Builder client(User client) {
            order.setClient(client);
            return this;
        }

        public Builder deliveryTime(Date deliveryTime) {
            order.setDeliveryTime(deliveryTime);
            return this;
        }


        public Builder pharmacist(User pharmacist) {
            order.setPharmacist(pharmacist);
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            order.setOrderStatus(orderStatus);
            return this;
        }

        public Builder paymentStatus(PaymentStatus paymentStatus) {
            order.setPaymentStatus(paymentStatus);
            return this;
        }

        public Builder orderEntries(List<OrderEntry> orderEntries) {
            order.setOrderEntries(orderEntries);
            return this;
        }

        public Builder prescriptions(List<Prescription> prescriptions) {
            order.setPrescriptions(prescriptions);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
