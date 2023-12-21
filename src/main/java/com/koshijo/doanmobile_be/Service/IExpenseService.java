package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;

import java.util.List;

public interface IExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
    List<BaseDto> getAllExpensesByMonthAndYear(Long userId, int month,long year);

    BaseDto updateExpense(Long expenseId, BaseDto expenseDto);
    String deleteExpense(Long userId, Long expenseId);
}
