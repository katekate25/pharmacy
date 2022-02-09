package com.epam.training.epharmacy.entity;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Prescription {
    private int prescriptionNumber;
    private User client;
    private User doctor;
    private Medicine medicine;
    private double packageAmount;
    private String usageInstruction;
    private Date creationDate;
    private Date expirationDate;
    private Integer orderEntryNumber;
    private PrescriptionStatus status;

    public Prescription() {}

    public int getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(int prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
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

    public String getUsageInstruction() {
        return usageInstruction;
    }

    public void setUsageInstruction(String usageInstruction) {
        this.usageInstruction = usageInstruction;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getOrderEntryNumber() {
        return orderEntryNumber;
    }

    public void setOrderEntryNumber(Integer orderEntryNumber) {
        this.orderEntryNumber = orderEntryNumber;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return prescriptionNumber == that.prescriptionNumber && Double.compare(that.packageAmount, packageAmount) == 0 && Objects.equals(client, that.client) && Objects.equals(doctor, that.doctor) && Objects.equals(medicine, that.medicine) && Objects.equals(usageInstruction, that.usageInstruction) && Objects.equals(creationDate, that.creationDate) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(orderEntryNumber, that.orderEntryNumber) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescriptionNumber, client, doctor, medicine, packageAmount, usageInstruction, creationDate, expirationDate, orderEntryNumber, status);
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionNumber=" + prescriptionNumber +
                ", client=" + client +
                ", doctor=" + doctor +
                ", medicine=" + medicine +
                ", packageAmount=" + packageAmount +
                ", usageInstruction='" + usageInstruction + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", orderEntryNumber=" + orderEntryNumber +
                ", status=" + status +
                '}';
    }

    public static class Builder {

        Prescription prescription;

        public Builder() {
            prescription = new Prescription();
        }

        public Builder prescriptionNumber(int prescriptionNumber){
            prescription.setPrescriptionNumber(prescriptionNumber);
            return this;
        }

        public Builder medicine(Medicine medicine){
            prescription.setMedicine(medicine);
            return this;
        }

        public Builder packageAmount(double packageAmount){
            prescription.setPackageAmount(packageAmount);
            return this;
        }

        public Builder usageInstruction(String usageInstruction){
            prescription.setUsageInstruction(usageInstruction);
            return this;
        }

        public Builder creationDate(Date creationDate){
            prescription.setCreationDate(creationDate);
            return this;
        }

        public Builder expirationDate(Date expirationDate){
            prescription.setExpirationDate(expirationDate);
            return this;
        }

        public Builder client(User client) {
            prescription.setClient(client);
            return this;
        }

        public Builder doctor(User doctor) {
            prescription.setDoctor(doctor);
            return this;
        }

        public Builder status(PrescriptionStatus status) {
            prescription.setStatus(status);
            return this;
        }

        public Prescription build()
        {
            return prescription;
        }
    }

}
