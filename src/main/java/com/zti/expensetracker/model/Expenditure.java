package com.zti.expensetracker.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenditure")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Expenditure() {}

    public Expenditure(String name, User creator, String description, double amount, Date date) {
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
