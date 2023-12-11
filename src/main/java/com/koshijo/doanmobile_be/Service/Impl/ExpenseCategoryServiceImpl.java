package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.BaseCategoryDto;
import com.koshijo.doanmobile_be.Entity.BudgetCategory;
import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import com.koshijo.doanmobile_be.Repository.BudgetCategoryRepository;
import com.koshijo.doanmobile_be.Repository.ExpenseCategoryRepository;
import com.koshijo.doanmobile_be.Service.IBudgetCategoryService;
import com.koshijo.doanmobile_be.Service.IExpenseCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryServiceImpl implements IExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }
    @Override
    public List<BaseCategoryDto> getAllExpenseCategoryForUser(Long userId) {
        List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.findExpenseCategoryByUser_Id(userId);
        return expenseCategoryList.stream().
                map(expenseCategory ->{
                    BaseCategoryDto temp = new BaseCategoryDto();
                    temp.setId(expenseCategory.getId());
                    temp.setCategoryIconPath(expenseCategory.getCategoryIcon().getCategoryPath());
                    temp.setCategoryName(expenseCategory.getCategoryName());
                    temp.setCategoryIconId(expenseCategory.getCategoryIcon().getId());
                    temp.setUserId(expenseCategory.getUser().getId());
                    temp.setType(expenseCategory.getType());
                    temp.setColor(expenseCategory.getColor());
                    return temp;
                }).toList();
    }
}
