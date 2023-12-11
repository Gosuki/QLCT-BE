package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.BaseCategoryDto;
import com.koshijo.doanmobile_be.Entity.BudgetCategory;
import com.koshijo.doanmobile_be.Repository.BudgetCategoryRepository;
import com.koshijo.doanmobile_be.Service.IBudgetCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetCategoryServiceImpl implements IBudgetCategoryService {
    private final BudgetCategoryRepository budgetCategoryRepository;

    public BudgetCategoryServiceImpl(BudgetCategoryRepository budgetCategoryRepository) {
        this.budgetCategoryRepository = budgetCategoryRepository;
    }
    @Override
    public List<BaseCategoryDto> getAllBudgetCategoryForUser(Long userId) {
        List<BudgetCategory> budgetCategoryList = budgetCategoryRepository.findBudgetCategoriesByUser_Id(userId);
        return budgetCategoryList.stream().
                map(budgetCategory ->{
                    BaseCategoryDto temp = new BaseCategoryDto();
                    temp.setId(budgetCategory.getId());
                    temp.setCategoryIconPath(budgetCategory.getCategoryIcon().getCategoryPath());
                    temp.setCategoryName(budgetCategory.getCategoryName());
                    temp.setCategoryIconId(budgetCategory.getCategoryIcon().getId());
                    temp.setUserId(budgetCategory.getUser().getId());
                    temp.setType(budgetCategory.getType());
                    temp.setColor(budgetCategory.getColor());
                    return temp;
                }).toList();
    }
}
