package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "`Order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;
    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<OrderLine> orderLines = new ArrayList<>();

    public Order() { }

    public Order(Date date) {
        this.date = date;
    }

    public Order(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public int getOrderid() {
        return orderid;
    }

    private void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Collection<OrderLine> getOrderLines() {
        return Collections.unmodifiableCollection(orderLines);
    }

    public boolean addOrderLine(OrderLine orderLine) {
        return this.orderLines.add(orderLine);
    }

    public boolean removeOrderLine(OrderLine orderLine) {
        return this.orderLines.remove(orderLine);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", date=" + date +
                ", orderLines=" + orderLines +
                '}';
    }
}
