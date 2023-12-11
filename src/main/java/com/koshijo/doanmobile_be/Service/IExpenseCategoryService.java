package com.koshijo.doanmobile_be.Service;

import com.koshijo.doanmobile_be.Dto.BaseCategoryDto;

import java.util.List;

public interface IExpenseCategoryService {
    List<BaseCategoryDto> getAllExpenseCategoryForUser(Long userId);
}
