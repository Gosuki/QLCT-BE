package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.ExpenseDto;

import java.text.ParseException;

public interface IExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);
}
