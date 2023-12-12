package com.koshijo.doanmobile_be.Convert;

import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Entity.Budget;
import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class BudgetConvert {
    public BudgetDto toDTO(Budget budget){
        BudgetDto budgetDto = new BudgetDto();
        if (budget.getId() != null){
            budgetDto.setId(budget.getId());
        }
        budgetDto.setUserId(budget.getUser().getId());
        budgetDto.setBudgetDate(budget.getBudgetDate().toString());
        budgetDto.setBudgetNote(budget.getBudgetNote());
        budgetDto.setBudgetAmount(budget.getBudgetAmount());
        budgetDto.setBudgetCategoryId(budget.getBudgetCategory().getId());
        budgetDto.setBudgetCategoryName(budget.getBudgetCategory().getCategoryName());
        budgetDto.setBudgetCategoryIcon(budget.getBudgetCategory().getCategoryIcon().getCategoryPath());
        return budgetDto;
    }
    public Budget toEntity(BudgetDto budgetDto){
        return null;
    }
}
