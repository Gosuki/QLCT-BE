package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.ExpenseDto;

import java.text.ParseException;
import java.util.List;

public interface IExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
    List<ExpenseDto> getAllExpensesByMonth(Long userId, int month);
}
