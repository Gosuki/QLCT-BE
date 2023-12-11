package com.koshijo.doanmobile_be.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {
    private Long id;
    private Long userId;
    private String budgetDate;
    private String budgetNote;
    private double budgetAmount;
    private Long budgetCategoryId ;

}
