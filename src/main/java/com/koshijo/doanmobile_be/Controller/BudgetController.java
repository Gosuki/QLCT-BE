package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Service.IBudgetService;
import com.koshijo.doanmobile_be.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {
    @Autowired
    private IBudgetService budgetService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse>createBudget(@RequestBody BudgetDto budgetDto) {
        BudgetDto budgetResponse = budgetService.createBudget(budgetDto);
        if (budgetResponse != null){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.CREATED.value(),budgetResponse,"Created"));
        } else {
                return ResponseEntity.badRequest().body(
                        new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Created Fail"));
        }
    }
    @PutMapping("/update/{budgetId}")
    public ResponseEntity<BaseResponse>updateBudget(@PathVariable(value = "budgetId") Long budgetId,@RequestBody BaseDto budgetDto) {
        BudgetDto budgetResponse = budgetService.updateBudget(budgetId,budgetDto);
        if (budgetResponse != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.UPGRADE_REQUIRED.value(),budgetResponse,"Created"));
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Created Fail"));
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getAllBudgetsByMonthAndYear(@PathVariable Long userId,
                                                             @RequestParam("month") int month,
                                                             @RequestParam("year") long year){
        List<BaseDto> budgetResponse = budgetService.getAllBudgetsByMonthAndYear(userId,month,year);
        if (budgetResponse != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),budgetResponse,"Get all budgets by month complete"));
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Get all budgets by month fail"));
        }
    }

    @DeleteMapping("/delete/{userId}/{budgetId}")

    public ResponseEntity<?> deleteExpense(@PathVariable(value = "userId") Long userId,
                                           @PathVariable(value = "budgetId") Long budgetId){
        String message = budgetService.deleteBudget(userId,budgetId);
        if (message != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),null,message));
        }
        else {
            return ResponseEntity.badRequest().body(new BaseResponse(HttpStatus.BAD_REQUEST.value(),null,message));
        }
    }
}
