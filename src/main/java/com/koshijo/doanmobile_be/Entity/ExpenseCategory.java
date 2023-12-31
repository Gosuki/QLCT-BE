package com.koshijo.doanmobile_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ExpenseCategories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private CategoryIcon categoryIcon;

    private String color;
    private Type type;
}
