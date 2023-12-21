package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findExpenseById(Long id);
    @Query(value = "select u from Expense u where u.user.Id=:userId and month(u.expenseDate) = :month and year(u.expenseDate) = :year")
    List<Expense> findExpensesByUserIdAndExpenseMonthOfDate(Long userId, int month,long year);

    Expense findExpenseByUserIdAndId(Long userId, Long expenseId);
}