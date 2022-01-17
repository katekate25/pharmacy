package com.epam.training.epharmacy.entity;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Medicine {
    private int id;
    private String commercialName;
    private String internationalName;
    private int medicineDose;
    private String medicineForm;
    private String serialNumber;
    private double packagePrice;
    private Date medicineExpirationDate;
    private String invoiceNumber;
    private Date arrivalDate;
    private Double productArrival;
    private Double productBalance;
    private boolean isPrescriptionRequired;
    private Producer producer;
    private DiseaseGroup diseaseGroup;



    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String medicineForm) {
        this.medicineForm = medicineForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public int getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(int medicineDose) {
        this.medicineDose = medicineDose;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Date getMedicineExpirationDate() {
        return medicineExpirationDate;
    }

    public void setMedicineExpirationDate(Date medicineExpirationDate) {
        this.medicineExpirationDate = medicineExpirationDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getProductArrival() {
        return productArrival;
    }

    public void setProductArrival(double productArrival) {
        this.productArrival = productArrival;
    }

    public double getProductBalance() {
        return productBalance;
    }

    public void setProductBalance(double productBalance) {
        this.productBalance = productBalance;
    }

    public boolean isPrescriptionRequired() {
        return isPrescriptionRequired;
    }

    public void setPrescriptionRequired(boolean prescriptionRequired) {
        isPrescriptionRequired = prescriptionRequired;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public DiseaseGroup getDiseaseGroup() {
        return diseaseGroup;
    }

    public void setDiseaseGroup(DiseaseGroup diseaseGroup) {
        this.diseaseGroup = diseaseGroup;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id == medicine.id && medicineDose == medicine.medicineDose && Double.compare(medicine.packagePrice, packagePrice) == 0 && Double.compare(medicine.productArrival, productArrival) == 0 && Double.compare(medicine.productBalance, productBalance) == 0 && isPrescriptionRequired == medicine.isPrescriptionRequired && Objects.equals(commercialName, medicine.commercialName) && Objects.equals(internationalName, medicine.internationalName) && Objects.equals(medicineForm, medicine.medicineForm) && Objects.equals(serialNumber, medicine.serialNumber) && Objects.equals(medicineExpirationDate, medicine.medicineExpirationDate) && Objects.equals(invoiceNumber, medicine.invoiceNumber) && Objects.equals(arrivalDate, medicine.arrivalDate) && Objects.equals(producer, medicine.producer) && diseaseGroup == medicine.diseaseGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commercialName, internationalName, medicineDose, medicineForm, serialNumber, packagePrice, medicineExpirationDate, invoiceNumber, arrivalDate, productArrival, productBalance, isPrescriptionRequired, producer, diseaseGroup);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", commercialName='" + commercialName + '\'' +
                ", internationalName='" + internationalName + '\'' +
                ", medicineDose=" + medicineDose +
                ", medicineForm='" + medicineForm + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", packagePrice=" + packagePrice +
                ", medicineExpirationDate=" + medicineExpirationDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", productArrival=" + productArrival +
                ", productBalance=" + productBalance +
                ", isPrescriptionRequired=" + isPrescriptionRequired +
                ", producer=" + producer +
                ", diseaseGroup=" + diseaseGroup +
                '}';
    }

    public static class Builder {
        Medicine medicine;

        public Builder() {
            medicine = new Medicine();
        }

        public Builder id(int id){
            medicine.setId(id);
            return this;
        }

        public Builder commercialName(String commercialName){
            medicine.setCommercialName(commercialName);
            return this;
        }

        public Builder internationalName(String internationalName){
            medicine.setInternationalName(internationalName);
            return this;
        }

        public Builder medicineDose(int medicineDose){
            medicine.setMedicineDose(medicineDose);
            return this;
        }

        public Builder medicineForm(String medicineForm){
            medicine.setMedicineForm(medicineForm);
            return this;
        }

        public Builder serialNumber(String serialNumber){
            medicine.setSerialNumber(serialNumber);
            return this;
        }

        public Builder packagePrice(double packagePrice){
            medicine.setPackagePrice(packagePrice);
            return this;
        }

        public Builder medicineExpirationDate(Date medicineExpirationDate){
            medicine.setMedicineExpirationDate(medicineExpirationDate);
            return this;
        }

        public Builder invoiceNumber(String invoiceNumber){
            medicine.setInvoiceNumber(invoiceNumber);
            return this;
        }

        public Builder arrivalDate(Date arrivalDate){
            medicine.setArrivalDate(arrivalDate);
            return this;
        }

        public Builder productArrival(Double productArrival){
            medicine.setProductArrival(productArrival);
            return this;
        }

        public Builder productBalance(Double productBalance){
            medicine.setProductBalance(productBalance);
            return this;
        }

        public Builder isPrescriptionRequired(boolean isPrescriptionRequired){
            medicine.setPrescriptionRequired(isPrescriptionRequired);
            return this;
        }


        public Builder producer(Producer producer){
            medicine.setProducer(producer);
            return this;
        }

        public Builder diseaseGroup(DiseaseGroup diseaseGroup){
            medicine.setDiseaseGroup(diseaseGroup);
            return this;
        }

        public Medicine build()
        {
            return medicine;
        }
    }


}
