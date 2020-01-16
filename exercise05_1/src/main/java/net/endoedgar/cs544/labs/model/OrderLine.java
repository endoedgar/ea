package net.endoedgar.cs544.labs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;

    public OrderLine() { }

    public OrderLine(int quantity) { this.quantity = quantity; }

    public OrderLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
