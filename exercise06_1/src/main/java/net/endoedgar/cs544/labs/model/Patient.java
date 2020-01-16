package net.endoedgar.cs544.labs.model;

import javax.persistence.*;

@Entity
@SecondaryTable(name = "Address",
        pkJoinColumns = @PrimaryKeyJoinColumn(name="PATIENT_ID", referencedColumnName = "ID")
)
public class Patient {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(table = "Address", name="STREET")
    private String street;
    @Column(table = "Address", name="ZIP")
    private String zip;
    @Column(table = "Address", name="CITY")
    private String city;

    public Patient() { }

    public Patient(String name, String street, String zip, String city) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
