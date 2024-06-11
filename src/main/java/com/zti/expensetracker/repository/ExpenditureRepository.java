package com.zti.expensetracker.repository;

import com.zti.expensetracker.model.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
    
}
