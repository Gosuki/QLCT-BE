package com.koshijo.doanmobile_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CategoryIcons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryIcon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(mappedBy = "categoryIcon")
    private ExpenseCategory expenseCategory;

    @Column(name = "category_path")
    private String categoryPath;

    @OneToOne(mappedBy = "categoryIcon")
    private BudgetCategory budgetCategory;
    @OneToOne(mappedBy = "icon")
    private Category category;
}
