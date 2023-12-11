package com.koshijo.doanmobile_be.Convert;

import com.koshijo.doanmobile_be.Dto.CategoryDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Entity.Category;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseConvert {
    public ExpenseDto toDTO(Expense expense){
        ExpenseDto expenseDto = new ExpenseDto();
        if (expense.getId() != null){
            expenseDto.setId(expense.getId());
        }
        expenseDto.setUserId(expense.getUser().getId());
        expenseDto.setExpenseDate(expense.getExpenseDate().toString());
        expenseDto.setExpenseNote(expense.getExpenseNote());
        expenseDto.setExpenseAmount(expense.getExpenseAmount());
        expenseDto.setExpenseCategoryId(expense.getExpenseCategory().getId());
        return expenseDto;
    }
    public Expense toEntity(ExpenseDto expenseDto){
        return null;
    }
}
