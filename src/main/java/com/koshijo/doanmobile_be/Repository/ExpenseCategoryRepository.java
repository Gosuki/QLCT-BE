package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.BudgetCategory;
import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findExpenseCategoryById(Long id);
    List<ExpenseCategory> findExpenseCategoryByUser_Id(Long id);
}