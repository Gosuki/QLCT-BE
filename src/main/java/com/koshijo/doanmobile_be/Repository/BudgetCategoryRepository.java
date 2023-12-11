package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.BudgetCategory;
import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
    Optional<BudgetCategory> findBudgetCategoryById(Long id);
    List<BudgetCategory> findBudgetCategoriesByUser_Id(Long id);
}