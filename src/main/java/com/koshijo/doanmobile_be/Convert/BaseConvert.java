package com.koshijo.doanmobile_be.Convert;

import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Entity.Budget;
import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class BaseConvert {
    public BaseDto toDTO_Budget(Budget budget){
        BaseDto budgetDto = new BaseDto();
        if (budget.getId() != null){
            budgetDto.setId(budget.getId());
        }
        budgetDto.setUserId(budget.getUser().getId());
        budgetDto.setDate(budget.getBudgetDate().toString());
        budgetDto.setNote(budget.getBudgetNote());
        budgetDto.setAmount(budget.getBudgetAmount());
        budgetDto.setCategoryId(budget.getBudgetCategory().getId());
        budgetDto.setCategoryName(budget.getBudgetCategory().getCategoryName());
        budgetDto.setCategoryIcon(budget.getBudgetCategory().getCategoryIcon().getCategoryPath());
        budgetDto.setColorIcon(budget.getBudgetCategory().getColor());
        return budgetDto;
    }
    public BaseDto toDTO_Expense(Expense expense){
        BaseDto dto = new BaseDto();
        if (expense.getId() != null){
            dto.setId(expense.getId());
        }
        dto.setUserId(expense.getUser().getId());
        dto.setDate(expense.getExpenseDate().toString());
        dto.setNote(expense.getExpenseNote());
        dto.setAmount(expense.getExpenseAmount());
        dto.setCategoryId(expense.getExpenseCategory().getId());
        dto.setCategoryName(expense.getExpenseCategory().getCategoryName());
        dto.setCategoryIcon(expense.getExpenseCategory().getCategoryIcon().getCategoryPath());
        dto.setColorIcon(expense.getExpenseCategory().getColor());
        return dto;
    }
    public Budget toEntity(BudgetDto budgetDto){
        return null;
    }
}
