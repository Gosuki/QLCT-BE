package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}