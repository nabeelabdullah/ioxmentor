package com.codingsuperstar.codingsuperstar.dto;

/**
 * Created by nabeelabdullah on 10/10/17.
 */
public class PayUMoneyDTO {

    public String split_info;
    public String firstname;
    public String additionalCharges;
    public String mode;
    public String hash;
    public String status;
    public String error_Message;
    public String paymentId;
    public String payuMoneyId;
    public String productinfo;
    public String email;
    public String txnid;
    public String mobile;
    public String amount;
    public String notificationId;

    public String getSplit_info() {
        return split_info;
    }

    public void setSplit_info(String split_info) {
        this.split_info = split_info;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(String additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_Message() {
        return error_Message;
    }

    public void setError_Message(String error_Message) {
        this.error_Message = error_Message;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayuMoneyId() {
        return payuMoneyId;
    }

    public void setPayuMoneyId(String payuMoneyId) {
        this.payuMoneyId = payuMoneyId;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String toString() {
        return "PayUMoneyDTO{" +
                "split_info='" + split_info + '\'' +
                ", firstname='" + firstname + '\'' +
                ", additionalCharges='" + additionalCharges + '\'' +
                ", mode='" + mode + '\'' +
                ", hash='" + hash + '\'' +
                ", status='" + status + '\'' +
                ", error_Message='" + error_Message + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", payuMoneyId='" + payuMoneyId + '\'' +
                ", productinfo='" + productinfo + '\'' +
                ", email='" + email + '\'' +
                ", txnid='" + txnid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", amount='" + amount + '\'' +
                ", notificationId='" + notificationId + '\'' +
                '}';
    }
}
