package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}