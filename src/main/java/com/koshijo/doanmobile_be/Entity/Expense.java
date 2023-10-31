package com.koshijo.doanmobile_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Expenses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "expense_amount")
    private double expenseAmount;

    @Column(name = "expense_date")
    private Date expenseDate;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "expense_note")
    private String expenseNote;
}
