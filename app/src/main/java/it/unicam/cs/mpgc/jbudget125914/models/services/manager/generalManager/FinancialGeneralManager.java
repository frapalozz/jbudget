package it.unicam.cs.mpgc.jbudget125914.models.services.manager.generalManager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialGeneralManager extends AbstractGeneralManager<
        FinancialTransaction,
        FinancialAmount,
        BigDecimal,
        FinancialAccount,
        LocalDate,
        FinancialTag,
        FinancialCategory,
        FinancialGroup,
        TransactionService<FinancialTransaction, FinancialAccount, FinancialTag, BigDecimal, LocalDate, FinancialAmount, FinancialGroup>,
        AccountService<FinancialAccount, BigDecimal, FinancialGroup>,
        CategoryService<FinancialAmount, BigDecimal, FinancialAccount, FinancialTransaction, FinancialTag, FinancialCategory, LocalDate>,
        GroupService<FinancialGroup>,
        TagService<FinancialTag, FinancialCategory>> {
}
