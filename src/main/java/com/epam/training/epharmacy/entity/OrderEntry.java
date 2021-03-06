package com.epam.training.epharmacy.entity;

import java.util.Objects;

public class OrderEntry {

    private int id;
    private Medicine medicine;
    private double packageAmount;
    private int orderNumber;
    private Prescription prescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntry entry = (OrderEntry) o;
        return id == entry.id && Double.compare(entry.packageAmount, packageAmount) == 0 && orderNumber == entry.orderNumber && Objects.equals(medicine, entry.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, packageAmount, orderNumber);
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", packageAmount=" + packageAmount +
                ", orderNumber=" + orderNumber +
                ", prescription=" + prescription +
                '}';
    }
}
