package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.*;
import com.koshijo.doanmobile_be.Exception.UserRegistrationException;
import com.koshijo.doanmobile_be.Repository.BudgetCategoryRepository;
import com.koshijo.doanmobile_be.Repository.CategoryRepository;
import com.koshijo.doanmobile_be.Repository.ExpenseCategoryRepository;
import com.koshijo.doanmobile_be.Repository.UserRepository;
import com.koshijo.doanmobile_be.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;

    public UserServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ExpenseCategoryRepository expenseCategoryRepository,
                           BudgetCategoryRepository budgetCategoryRepository){
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
    }
    @Override
    public User createUser(UserDto userDto) {
        try {
            Optional<User> existUser=userRepository.findUserByEmail(userDto.getEmail());
            if(existUser.isPresent()){
                throw new UserRegistrationException("Người dùng đã tồn tại.");
            }
            User user = new User();
            user.setUserName(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            User savedUser = userRepository.save(user);
            renderCategoryData(user);
            return user;
        } catch (UserRegistrationException ex){
            throw ex;
        }catch (Exception ex) {
            throw new UserRegistrationException("Lỗi khi đăng ký người dùng.");
        }
    }

    @Override
    public Boolean login(UserDto userDto) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(userDto.getEmail(),userDto.getPassword());
        return user.isPresent();
    }
    public void renderCategoryData(User user){
        String[] categoryExpenseDefault = {"Ăn uống", "Quần áo", "Chi tiêu hàng ngày","Mỹ Phẩm","Y tế","Tiền điện","Đi lại","Tiền nhà"};
        String[] categoryBudgetDefault = {"Tiền lương","Tiền phụ cấp","Tiền thưởng","Thu nhập phụ","Đầu tư","Thu nhập tạm thời"};
        List<ExpenseCategory> listCategoryExpenseDefault = categoryRepository.getCategoriesByType(Type.TYPE_EXPENSE)
                .stream().filter(category -> Arrays.asList(categoryExpenseDefault).contains(category.getCategoryName()))
                .map(category -> {
                    ExpenseCategory expenseCategory = new ExpenseCategory();
                    expenseCategory.setCategoryName(category.getCategoryName());
                    expenseCategory.setCategoryIcon(category.getIcon());
                    expenseCategory.setUser(user);
                    expenseCategory.setType(category.getType());
                    expenseCategory.setColor(category.getColor());
                    return expenseCategory;
                })
                .toList();
        List<BudgetCategory> listCategoryBudgetDefault = categoryRepository.getCategoriesByType(Type.TYPE_BUDGET)
                .stream().filter(category -> Arrays.asList(categoryBudgetDefault).contains(category.getCategoryName()))
                .map(category -> {
                    BudgetCategory budgetCategory = new BudgetCategory();
                    budgetCategory.setCategoryName(category.getCategoryName());
                    budgetCategory.setCategoryIcon(category.getIcon());
                    budgetCategory.setUser(user);
                    budgetCategory.setType(category.getType());
                    budgetCategory.setColor(category.getColor());
                    return budgetCategory;
                })
                .toList();
        budgetCategoryRepository.saveAll(listCategoryBudgetDefault);
        expenseCategoryRepository.saveAll(listCategoryExpenseDefault);
    }
}
