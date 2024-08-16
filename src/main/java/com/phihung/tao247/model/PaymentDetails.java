package com.phihung.tao247.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentDetails {
    private String paymentMethod;
    private String status;
    private String paymentId;
    private String zalopayPaymentLinkId;
    private String zalopayPaymentLinkReferenceId;
    private String zalopayPaymentLinkStatus;
    private String zalopayPaymentId;

    public PaymentDetails() {

    }

    public PaymentDetails(String paymentMethod, String status, String paymentId, String zalopayPaymentLinkId, String zalopayPaymentLinkReferenceId, String zalopayPaymentLinkStatus, String zalopayPaymentId) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.zalopayPaymentLinkId = zalopayPaymentLinkId;
        this.zalopayPaymentLinkReferenceId = zalopayPaymentLinkReferenceId;
        this.zalopayPaymentLinkStatus = zalopayPaymentLinkStatus;
        this.zalopayPaymentId = zalopayPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getZalopayPaymentLinkId() {
        return zalopayPaymentLinkId;
    }

    public void setZalopayPaymentLinkId(String zalopayPaymentLinkId) {
        this.zalopayPaymentLinkId = zalopayPaymentLinkId;
    }

    public String getZalopayPaymentLinkReferenceId() {
        return zalopayPaymentLinkReferenceId;
    }

    public void setZalopayPaymentLinkReferenceId(String zalopayPaymentLinkReferenceId) {
        this.zalopayPaymentLinkReferenceId = zalopayPaymentLinkReferenceId;
    }

    public String getZalopayPaymentLinkStatus() {
        return zalopayPaymentLinkStatus;
    }

    public void setZalopayPaymentLinkStatus(String zalopayPaymentLinkStatus) {
        this.zalopayPaymentLinkStatus = zalopayPaymentLinkStatus;
    }

    public String getZalopayPaymentId() {
        return zalopayPaymentId;
    }

    public void setZalopayPaymentId(String zalopayPaymentId) {
        this.zalopayPaymentId = zalopayPaymentId;
    }
}
