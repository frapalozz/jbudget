package it.unicam.cs.mpgc.jbudget125914.models.repository.transaction;

import it.unicam.cs.mpgc.jbudget125914.service.filterService.FinancialFilterService;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;

public class FinancialScheduleRepository extends AbstractFinancialTransactionRepository<FinancialSchedule> implements TransactionRepository<FinancialSchedule, FinancialFilterService> {

    public FinancialScheduleRepository() {
        super(FinancialSchedule.class);
    }
}
