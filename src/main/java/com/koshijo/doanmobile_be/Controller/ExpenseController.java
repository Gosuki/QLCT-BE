package com.koshijo.doanmobile_be.Controller;

import com.koshijo.doanmobile_be.Dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses/v1")
public class ExpenseController {
    public ResponseEntity<BaseResponse>createExpense(){
        return null;
    }
}
