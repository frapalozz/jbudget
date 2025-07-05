/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpgc.jbudget125914.controller.manager.generalManager;

import it.unicam.cs.mpgc.jbudget125914.controller.dao.*;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represent a Financial General Manager
 */
public class FinancialGeneralManager extends AbstractGeneralManager<
        FinancialTransaction,
        FinancialSchedule,
        FinancialAmount,
        BigDecimal,
        FinancialAccount,
        LocalDate,
        FinancialTag,
        FinancialCategory,
        FinancialGroup,
        TransactionDAO<FinancialTransaction, FinancialAccount, FinancialTag, BigDecimal, LocalDate, FinancialAmount, FinancialGroup>,
        AccountDAO<FinancialAccount>,
        CategoryDAO<FinancialCategory>,
        GroupDAO<FinancialGroup>,
        TagDAO<FinancialTag, FinancialCategory>,
        ScheduleDAO<FinancialSchedule, FinancialAccount, FinancialTag, BigDecimal, LocalDate, FinancialAmount, FinancialGroup>> {
}
