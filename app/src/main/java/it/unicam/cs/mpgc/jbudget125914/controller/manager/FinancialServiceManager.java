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

package it.unicam.cs.mpgc.jbudget125914.controller.manager;

import it.unicam.cs.mpgc.jbudget125914.models.dao.GeneralDAO;
import it.unicam.cs.mpgc.jbudget125914.models.dao.ScheduleDAO;
import it.unicam.cs.mpgc.jbudget125914.models.dao.TransactionDAO;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.fetchManager.FinancialFetchManager;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.FinancialFilterManager;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.daoManager.FinancialDaoManager;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This class represent a Financial service manager.
 * It is as singleton, used to maintain a single controller for all the UI
 */
public class FinancialServiceManager extends AbstractServiceManager<
        FinancialTransaction,
        FinancialSchedule,
        FinancialAccount,
        FinancialTag,
        BigDecimal,
        LocalDate,
        FinancialAmount,
        FinancialCategory,
        FinancialGroup,
        FinancialDaoManager,
        FinancialFilterManager,
        FinancialFetchManager> {

    private FinancialServiceManager() {
        setFetchManager(new FinancialFetchManager());
        setFilterManager(new FinancialFilterManager());
        setDaoManager(new FinancialDaoManager());
        getDaoManager().setTransactionDAO(new TransactionDAO<>(FinancialTransaction.class));
        getDaoManager().setAccountDAO(new GeneralDAO<>(FinancialAccount.class));
        getDaoManager().setCategoryDAO(new GeneralDAO<>(FinancialCategory.class));
        getDaoManager().setGroupDAO(new GeneralDAO<>(FinancialGroup.class));
        getDaoManager().setTagDAO(new GeneralDAO<>(FinancialTag.class));
        getDaoManager().setScheduleDAO(new ScheduleDAO<>(FinancialSchedule.class));
    }

    private static FinancialServiceManager instance = new FinancialServiceManager();

    /**
     * Return the instance of this FinancialServiceManager
     * @return the instance of this FinancialServiceManager
     */
    public static FinancialServiceManager getInstance() {
        if(instance == null) {
            instance = new FinancialServiceManager();
        }
        return instance;
    }

    @Override
    public void update() {
        getFetchManager().update(getDaoManager(), getFilterManager(), this::reflectUpdate);
    }

    private void reflectUpdate() {
        getChanges().set(!getChanges().getValue());
    }
}
