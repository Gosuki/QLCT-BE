package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
}