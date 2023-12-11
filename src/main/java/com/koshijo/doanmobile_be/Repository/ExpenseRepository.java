package com.koshijo.doanmobile_be.Repository;

import com.koshijo.doanmobile_be.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findExpenseById(Long id);
}