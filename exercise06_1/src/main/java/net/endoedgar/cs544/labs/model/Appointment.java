package net.endoedgar.cs544.labs.model;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="APPDATE")
    private String appdate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PATIENT")
    private Patient patient;
    @Embedded
    private Payment payment;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="DOCTOR")
    private Doctor doctor;

    public Appointment() { }

    public Appointment(String appdate, Patient patient, Payment payment, Doctor doctor) {
        this.appdate = appdate;
        this.patient = patient;
        this.payment = payment;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getAppdate() {
        return appdate;
    }

    public void setAppdate(String appdate) {
        this.appdate = appdate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appdate='" + appdate + '\'' +
                ", patient=" + patient +
                ", payment=" + payment +
                ", doctor=" + doctor +
                '}';
    }
}
