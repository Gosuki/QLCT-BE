package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query(value = "select u from Budget u where u.user.Id=:userId and month(u.budgetDate) = :month")
    List<Budget> findBudgetsByUserIdAndBudgetMonthOfDate(Long userId, int month);
}