package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Convert.BudgetConvert;
import com.koshijo.doanmobile_be.Dto.BudgetDto;
import com.koshijo.doanmobile_be.Entity.Budget;
import com.koshijo.doanmobile_be.Repository.*;
import com.koshijo.doanmobile_be.Service.IBudgetService;
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
    public List<BudgetDto> getAllBudgetsByMonth(Long userId, int month) {
        List<Budget> budgetList = budgetRepository.findBudgetsByUserIdAndBudgetMonthOfDate(userId,month);
        return budgetList.stream().map(budget -> budgetConvert.toDTO(budget)).toList();
    }

}
