package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    @Query(value = "select u from Budget u where" +
            " u.user.Id=:userId and" +
            " month(u.budgetDate) = :month and" +
            " year (u.budgetDate) = :year")
    List<Budget> findBudgetsByUserIdAndBudgetMonthOfDate(Long userId, int month,long year);
    Budget findBudgetByUserIdAndId(Long userId,Long id);
    void deleteBudgetByIdAndUserId(Long budgetId, Long userId);
}