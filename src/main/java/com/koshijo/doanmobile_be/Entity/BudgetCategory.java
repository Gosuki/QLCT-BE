package com.koshijo.doanmobile_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BudgetCategories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BudgetCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_name")
    private String categoryName;

    @OneToOne
    @JoinColumn(name = "icon_id")
    private CategoryIcon categoryIcon;

    private String color;
    private String type;

}
