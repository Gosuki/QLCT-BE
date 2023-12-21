package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Convert.BaseConvert;
import com.koshijo.doanmobile_be.Convert.BudgetConvert;
import com.koshijo.doanmobile_be.Dto.BaseDto;
import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Entity.Budget;
import com.koshijo.doanmobile_be.Repository.*;
import com.koshijo.doanmobile_be.Service.IBudgetService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class BudgetServiceImpl implements IBudgetService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(BudgetServiceImpl.class);
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final BudgetRepository budgetRepository;
    @Autowired
    private BudgetConvert budgetConvert;
    @Autowired
    private BaseConvert baseConvert;

    public BudgetServiceImpl(UserRepository userRepository, BudgetCategoryRepository budgetCategoryRepository, BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
        this.budgetRepository = budgetRepository;
    }
    @Override
    public BudgetDto createBudget(BudgetDto budgetDto)  {
        Budget budget = new Budget();
        budget.setUser(userRepository.findUserById(budgetDto.getUserId()).get());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy", new Locale("vi", "VN"));
        logger.info(budgetDto.getBudgetDate());
        try {
            Date date = sdf.parse(budgetDto.getBudgetDate());
            if (date != null) {
                budget.setBudgetDate(date);
            } else {
                logger.error("Parsing ngày thất bại.");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        budget.setBudgetNote(budgetDto.getBudgetNote());
        budget.setBudgetAmount(budgetDto.getBudgetAmount());
        budget.setBudgetCategory(budgetCategoryRepository.findBudgetCategoryById(budgetDto.getBudgetCategoryId()).get());
        budgetRepository.save(budget);
        return budgetConvert.toDTO(budget);
    }

    @Override
    public List<BaseDto> getAllBudgetsByMonthAndYear(Long userId, int month,long year) {
        List<Budget> budgetList = budgetRepository.findBudgetsByUserIdAndBudgetMonthOfDate(userId,month,year);
        return budgetList.stream().map(budget -> baseConvert.toDTO_Budget(budget)).toList();
    }

    @Override
    public BudgetDto updateBudget(Long budgetId,BaseDto budgetDto) {
        Budget budget = budgetRepository.findBudgetByUserIdAndId(budgetDto.getUserId(),budgetId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", new Locale("vi", "VN"));
        logger.info(budgetDto.getDate());
        try {
            Date date = sdf.parse(budgetDto.getDate());
            if (date != null) {
                budget.setBudgetDate(date);
            } else {
                logger.error("Parsing ngày thất bại.");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        budget.setBudgetNote(budgetDto.getNote());
        budget.setBudgetAmount(budgetDto.getAmount());
        budget.setBudgetCategory(budgetCategoryRepository.findBudgetCategoryById(budgetDto.getCategoryId()).get());
        budgetRepository.save(budget);
        return budgetConvert.toDTO(budget);
    }

    @Override
    @Transactional
    public String deleteBudget(Long userId, Long budgetId) {
        try {
            budgetRepository.deleteBudgetByIdAndUserId(budgetId, userId);
            return "Delete complete";
        }
        catch (Exception e){
            return null;
        }
    }

}
