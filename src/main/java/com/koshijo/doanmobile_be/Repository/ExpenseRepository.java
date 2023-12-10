package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findExpenseById(Long id);
}