package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.*;
import com.koshijo.doanmobile_be.Service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    @PutMapping("/update/{expenseId}")
    public ResponseEntity<BaseResponse>updateExpense(@PathVariable(value = "expenseId") Long expenseId,@RequestBody BaseDto expenseDto) {
        BaseDto expenseResponse = expenseService.updateExpense(expenseId,expenseDto);
        if (expenseResponse != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.UPGRADE_REQUIRED.value(),expenseResponse,"Created"));
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Created Fail"));
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getAllExpensesByMonthAndYear(@PathVariable Long userId,
                                                                     @RequestParam("month") int month,
                                                                     @RequestParam("year") long year){
        List<BaseDto> expenseResponse = expenseService.getAllExpensesByMonthAndYear(userId,month,year);
        if (expenseResponse != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),expenseResponse,"Get all expenses by month complete"));
        } else {
            return ResponseEntity.badRequest().body(
                    new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Get all expenses by month fail"));
        }
    }
    @DeleteMapping("/delete/{userId}/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable(value = "userId") Long userId,
                                                      @PathVariable(value = "expenseId") Long expenseId){
        String message = expenseService.deleteExpense(userId,expenseId);
        if (message != null){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),null,message));
        }
        else {
            return ResponseEntity.badRequest().body(new BaseResponse(HttpStatus.BAD_REQUEST.value(),null,message));
        }
    }
}
