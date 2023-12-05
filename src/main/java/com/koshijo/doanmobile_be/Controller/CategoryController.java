package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.CategoryDto;
import com.koshijo.doanmobile_be.Dto.CategoryIconDto;
import com.koshijo.doanmobile_be.Repository.CategoryIconRepository;
import com.koshijo.doanmobile_be.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryIconRepository categoryIconRepository;
    @Autowired
    private ICategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createCategory(@RequestBody CategoryDto categoryDto){
            CategoryDto categoryDtoResponse = categoryService.createCategory(categoryDto);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),categoryDtoResponse,"Created"));
    }
    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCategories(){
        try{
            List<CategoryDto> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),categories,"Get All Categories"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }
    }
    @GetMapping("/{type}")
    public ResponseEntity<BaseResponse> getCategoriesByType(@PathVariable int type){
        try{
            String message = "Get All Categories by ";
            List<CategoryDto> categories = categoryService.getAllCategoriesByType(type);
            if (type == 0){
                message = message + "Expense";
                return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),categories,message));
            } else if (type == 1) {
                message = message + "Budget";
                return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),categories,message));
            }
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), categories,"Not found Type"));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), e,e.getMessage()));
        }
    }

}
