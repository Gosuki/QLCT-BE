package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.BaseCategoryDto;

import java.util.List;

public interface IBudgetCategoryService {
    List<BaseCategoryDto> getAllBudgetCategoryForUser(Long userId);
}
