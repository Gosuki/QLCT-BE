package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import com.koshijo.doanmobile_be.Dto.CategoryDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    @Autowired
    private IExpenseService expenseService;
    @PostMapping("/create")
    public ResponseEntity<BaseResponse>createExpense(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto expenseResponse = expenseService.createExpense(expenseDto);
        if (expenseResponse != null){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.CREATED.value(),expenseDto,"Created"));
        } else {
                return ResponseEntity.badRequest().body(
                        new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Created Fail"));
        }
    }
}
