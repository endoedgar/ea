package net.endoedgar.cs544.labs.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Payment {
    @Column(name="PAYDATE")
    private String paydate;
    @Column(name="AMOUNT")
    private double amount;

    public Payment(String paydate, double amount) {
        this.paydate = paydate;
        this.amount = amount;
    }

    public Payment() { }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paydate='" + paydate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
