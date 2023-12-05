package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Convert.CategoryConvert;
import com.koshijo.doanmobile_be.Dto.CategoryDto;
import com.koshijo.doanmobile_be.Entity.Category;
import com.koshijo.doanmobile_be.Entity.CategoryIcon;
import com.koshijo.doanmobile_be.Entity.Type;
import com.koshijo.doanmobile_be.Repository.CategoryIconRepository;
import com.koshijo.doanmobile_be.Repository.CategoryRepository;
import com.koshijo.doanmobile_be.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryIconRepository categoryIconRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConvert categoryConvert;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(category.getCategoryName());
        category.setType(categoryDto.getType());
        category.setColor(categoryDto.getColor());
        Optional<CategoryIcon> categoryIcon = categoryIconRepository.findById(categoryDto.getIconId());
        category.setIcon(categoryIcon.get());
        categoryRepository.save(category);
        return categoryConvert.toDTO(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category ->categoryConvert.toDTO(category)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByType(int type) {
        Type temp;
        List<Category> categoryList;
        List<CategoryDto> defaultCategory;
        switch (type) {
            case 0 -> {temp = Type.TYPE_EXPENSE;categoryList = categoryRepository.getCategoriesByType(temp);
                return categoryList.stream().map(category ->categoryConvert.toDTO(category)).collect(Collectors.toList());}
            case 1 -> {temp = Type.TYPE_BUDGET;categoryList = categoryRepository.getCategoriesByType(temp);
                return categoryList.stream().map(category ->categoryConvert.toDTO(category)).collect(Collectors.toList());}
        }
        return null;
    }
}
