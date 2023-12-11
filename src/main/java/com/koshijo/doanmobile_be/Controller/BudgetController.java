package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Service.IBudgetService;
import com.koshijo.doanmobile_be.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {
    @Autowired
    private IBudgetService budgetService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse>createExpense(@RequestBody BudgetDto budgetDto) {
        BudgetDto budgetResponse = budgetService.createBudget(budgetDto);
        if (budgetResponse != null){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.CREATED.value(),budgetResponse,"Created"));
        } else {
                return ResponseEntity.badRequest().body(
                        new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Created Fail"));
        }
    }
}
