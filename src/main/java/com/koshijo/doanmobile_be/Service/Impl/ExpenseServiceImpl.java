package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Convert.BaseConvert;
import com.koshijo.doanmobile_be.Convert.ExpenseConvert;
import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.ExpenseDto;
import com.koshijo.doanmobile_be.Entity.Budget;
import com.koshijo.doanmobile_be.Entity.Expense;
import com.koshijo.doanmobile_be.Repository.ExpenseCategoryRepository;
import com.koshijo.doanmobile_be.Repository.ExpenseRepository;
import com.koshijo.doanmobile_be.Repository.UserRepository;
import com.koshijo.doanmobile_be.Service.IExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Service
public class ExpenseServiceImpl implements IExpenseService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseConvert expenseConvert;
    @Autowired
    private BaseConvert baseConvert;

    public ExpenseServiceImpl(UserRepository userRepository, ExpenseCategoryRepository expenseCategoryRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseRepository = expenseRepository;
    }
    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto)  {
        Expense expense = new Expense();
        expense.setUser(userRepository.findUserById(expenseDto.getUserId()).get());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
        logger.info(expenseDto.getExpenseDate());
        try {
            Date date = sdf.parse(expenseDto.getExpenseDate());
            if (date != null) {
                expense.setExpenseDate(date);
            } else {
                logger.error("Parsing ngày thất bại.");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        expense.setExpenseNote(expenseDto.getExpenseNote());
        expense.setExpenseAmount(expenseDto.getExpenseAmount());
        expense.setExpenseCategory(expenseCategoryRepository.findExpenseCategoryById(expenseDto.getExpenseCategoryId()).get());
        expenseRepository.save(expense);
        return expenseConvert.toDTO(expense);
    }

    @Override
    public List<BaseDto> getAllExpensesByMonthAndYear(Long userId, int month, long year) {
        List<Expense> expenseList = expenseRepository.findExpensesByUserIdAndExpenseMonthOfDate(userId,month,year);
        return expenseList.stream().map(expense -> baseConvert.toDTO_Expense(expense)).toList();
    }

    @Override
    public BaseDto updateExpense(Long expenseId, BaseDto expenseDto) {
        Expense expense = expenseRepository.findExpenseByUserIdAndId(expenseDto.getUserId(),expenseId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", new Locale("vi", "VN"));
        logger.info(expenseDto.getDate());
        try {
            Date date = sdf.parse(expenseDto.getDate());
            if (date != null) {
                expense.setExpenseDate(date);
            } else {
                logger.error("Parsing ngày thất bại.");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        expense.setExpenseNote(expenseDto.getNote());
        expense.setExpenseAmount(expenseDto.getAmount());
        expense.setExpenseCategory(expenseCategoryRepository.findExpenseCategoryById(expenseDto.getCategoryId()).get());
        expenseRepository.save(expense);
        return baseConvert.toDTO_Expense(expense);
    }
}
