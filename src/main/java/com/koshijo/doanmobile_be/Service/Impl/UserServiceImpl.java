package com.koshijo.doanmobile_be.Service.Impl;

import com.koshijo.doanmobile_be.Dto.ChangePasswordRequest;
import com.koshijo.doanmobile_be.Dto.UserDto;
import com.koshijo.doanmobile_be.Entity.*;
import com.koshijo.doanmobile_be.Repository.BudgetCategoryRepository;
import com.koshijo.doanmobile_be.Repository.CategoryRepository;
import com.koshijo.doanmobile_be.Repository.ExpenseCategoryRepository;
import com.koshijo.doanmobile_be.Repository.UserRepository;
import com.koshijo.doanmobile_be.Service.IUserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;



@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final BudgetCategoryRepository budgetCategoryRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           ExpenseCategoryRepository expenseCategoryRepository,
                           BudgetCategoryRepository budgetCategoryRepository, EmailService emailService){
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.budgetCategoryRepository = budgetCategoryRepository;
        this.emailService = emailService;
    }
    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        try {
            Optional<User> existUser = userRepository.findUserByEmail(userDto.getEmail());
            if (existUser.isPresent()) {
                return null;
//                throw new UserRegistrationException("Người dùng đã tồn tại.");
            }
            if (!Objects.equals(userDto.getPassword(), userDto.getRepeatPassword())) {
                return null;
//                throw new UserRegistrationException("Password không trùng nhau");
            }
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            User savedUser = userRepository.save(user);
            renderCategoryData(savedUser);
            UserDto dto = new UserDto();
            dto.setUserId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setPassword(user.getPassword());
            dto.setRepeatPassword("");
            return dto;
        } catch (Exception e){
            return null;
        }
//        } catch (UserRegistrationException ex){
//            throw ex;
//        }catch (Exception ex) {
//            throw new UserRegistrationException("Lỗi khi đăng ký người dùng.");
//        }
    }

    @Override
    public UserDto login(UserDto userDto) {
        Optional<User> user = userRepository.findUserByEmailAndPassword(userDto.getEmail(),userDto.getPassword());
        if (user.isPresent()){
            UserDto dto = new UserDto();
            dto.setUserId(user.get().getId());
            dto.setEmail(user.get().getEmail());
            dto.setPassword(user.get().getPassword());
            dto.setRepeatPassword("");
            return dto;
        }
        return null;

    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> user = userRepository.findUserById(userId);
        if (user.isPresent()){
            UserDto dto = new UserDto();
            dto.setUserId(user.get().getId());
            dto.setEmail(user.get().getEmail());
            dto.setPassword(user.get().getPassword());
            dto.setRepeatPassword("");
            return dto;
        }
        return null;
    }

    @Override
    @Transactional
    public UserDto changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        Optional<User> user = userRepository.findUserById(userId);
        if (user.isPresent()){
            if(changePasswordRequest.getCurrentPassword().equals(user.get().getPassword()) ){
                if(changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())){
                    user.get().setPassword(changePasswordRequest.getNewPassword());
                    User saved = userRepository.save(user.get());
                    UserDto dto = new UserDto();
                    dto.setUserId(saved.getId());
                    dto.setEmail(saved.getEmail());
                    dto.setPassword(saved.getPassword());
                    dto.setRepeatPassword("");
                    return dto;
                }
            }
        }
        return null;
    }

    @Override
    public UserDto forgotPassword(String email) {
        Optional<User> existUser = userRepository.findUserByEmail(email);
        if (existUser.isPresent()){
            String password = generateRandomPassword(email);
            existUser.get().setPassword(password);
            User saved = userRepository.save(existUser.get());
            UserDto dto = new UserDto();
            dto.setUserId(saved.getId());
            dto.setEmail(saved.getEmail());
            dto.setPassword(saved.getPassword());
            dto.setRepeatPassword("");
            return dto;
        }
        return null;
    }

    public void renderCategoryData(User user){
        String[] categoryExpenseDefault = {"Ăn uống","Chi tiêu hàng ngày", "Quần áo","Mỹ phẩm","Phí giao lưu","Y tế","Giáo dục","Tiền điện","Đi lại","Phí liên lạc","Tiền nhà"};
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
    public String generateRandomPassword(String email) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }
        try {
            emailService.sendEmail(email, "Forgot Password", "New password: " + password);
        } catch (Exception e) {
            logger.error("Send email failed");
            throw e;
        }
        logger.info("Send email activate successfully");
        return password.toString();
    }

}
