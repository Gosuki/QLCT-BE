package com.koshijo.doanmobile_be.Convert;

import com.koshijo.doanmobile_be.Dto.CategoryDto;
import com.koshijo.doanmobile_be.Entity.Category;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import org.springframework.stereotype.Component;

@Component
public class CategoryConvert {
    public CategoryDto toDTO(Category category){
        CategoryDto categoryDto = new CategoryDto();
        if (category.getId() != null){
            categoryDto.setId(category.getId());
        }
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setType(category.getType());
        categoryDto.setColor(category.getColor());
        CategoryIcon categoryIcon = category.getIcon();
        categoryDto.setIconId(categoryIcon.getId());
        categoryDto.setIconPath(categoryIcon.getCategoryPath());
        return categoryDto;
    }
    public Category toEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryName(category.getCategoryName());
        category.setType(category.getType());
        category.setColor(category.getColor());
        return category;
    }
}
