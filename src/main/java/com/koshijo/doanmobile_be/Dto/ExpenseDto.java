package com.koshijo.doanmobile_be.Dto;

import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import com.koshijo.doanmobile_be.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {
    private Long id;
    private Long userId;
    private String expenseDate;
    private String expenseNote;
    private double expenseAmount;
    private Long expenseCategoryId ;
    private String expenseCategoryName;
    private String expenseCategoryIcon;

}
