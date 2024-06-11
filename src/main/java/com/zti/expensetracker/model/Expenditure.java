package com.zti.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "expenditure")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "expenditure", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("expenditure")
    private Set<ExpenditureBudget> budgets;

    public Expenditure() {}

    public Expenditure(String name, User creator, String description, BigDecimal amount, Date date) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<ExpenditureBudget> getBudgets() {
        return budgets;
    }

    public void setBudgets(Set<ExpenditureBudget> budgets) {
        this.budgets = budgets;
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
