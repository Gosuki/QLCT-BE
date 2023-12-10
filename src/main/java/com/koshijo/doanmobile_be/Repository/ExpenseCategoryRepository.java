package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findExpenseCategoryByCategoryId(Long id);
}