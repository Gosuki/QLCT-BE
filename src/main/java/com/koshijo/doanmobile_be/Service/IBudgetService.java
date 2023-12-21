package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.BudgetDto;

import java.util.List;

public interface IBudgetService {
    BudgetDto createBudget(BudgetDto budgetDto);
    List<BaseDto> getAllBudgetsByMonthAndYear(Long userId, int month,long year);
    BudgetDto updateBudget(Long budgetId,BaseDto budgetDto);

    String deleteBudget(Long userId, Long budgetId);
}
