package com.epam.training.epharmacy.entity;

import java.util.Objects;

public class Producer {

    private int id;
    private String producerFactoryName;
    private String producerCountry;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducerFactoryName() {
        return producerFactoryName;
    }

    public void setProducerFactoryName(String producerFactoryName) {
        this.producerFactoryName = producerFactoryName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return producerFactoryName.equals(producer.producerFactoryName) && producerCountry.equals(producer.producerCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producerFactoryName, producerCountry);
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", producerFactoryName='" + producerFactoryName + '\'' +
                ", producerCountry='" + producerCountry + '\'' +
                '}';
    }
}
