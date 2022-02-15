package com.epam.training.epharmacy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Message implements Serializable {
    private Integer id;
    private String message;
    private Date messageDate;
    private User recipient;
    private User sender;
    private boolean approved;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return approved == message1.approved && Objects.equals(id, message1.id) && Objects.equals(message, message1.message) && Objects.equals(messageDate, message1.messageDate) && Objects.equals(recipient, message1.recipient) && Objects.equals(sender, message1.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, messageDate, recipient, sender, approved);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", isApproved=" + approved +
                '}';
    }
}
