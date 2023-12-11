package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.BaseCategoryDto;
import com.koshijo.doanmobile_be.Service.IBudgetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgetCategories")
public class BudgetCategoryController {
    @Autowired
    private IBudgetCategoryService budgetCategoryService;
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getAllBudgetCategoryForUser(@PathVariable Long userId ){
        try{
            List<BaseCategoryDto> baseCategoryDtoList = budgetCategoryService.getAllBudgetCategoryForUser(userId);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), baseCategoryDtoList,"Get All Budget Categories for user " + userId));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }
    }
}
