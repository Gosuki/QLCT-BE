package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    List<CategoryDto> getAllCategoriesByType(int type);
}
