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

package it.unicam.cs.mpgc.jbudget125914.controller.dao;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import lombok.NonNull;

import java.time.temporal.Temporal;
import java.util.*;

/**
 * Category DAO represent a category DAO
 * @param <AM> amount type
 * @param <N> number type
 * @param <A> account type
 * @param <T> transaction type
 * @param <TA> tag type
 * @param <C> category type
 * @param <D> data type
 * @param <G> group type
 */
public class CategoryDAO<
        AM extends Amount<N,AM>,
        N extends Number,
        A extends Account<AM>,
        T extends Transaction<AM,D,TA,A>,
        TA extends Tag<C>,
        C extends Category<TA>,
        D extends Temporal & Comparable<? super D>,
        G extends Group<TA,C,?,A>
        > extends AbstractDAO<C> {

    /**
     * CategoryDAO constructor
     * @param entityClass category class
     */
    public CategoryDAO(@NonNull Class<C> entityClass) {
        super(entityClass);
    }

    /**
     * Return a list of two maps, the first map contains category-income, and the second one contains category-expenses
     * These map connect the category with the amount of transaction
     * @param transactions filtered transaction
     * @return a list of two maps, the first map contains category-income, and the second one contains category-expenses
     */
    public List<Map<C, AM>> getCategoryBalance(List<T> transactions) {
        if(transactions == null) return Collections.emptyList();
        List<Map<C, AM>> categoryBalance = new ArrayList<>();
        categoryBalance.add(new HashMap<>());
        categoryBalance.add(new HashMap<>());

        transactions.forEach(t ->
            t.getTags().stream()
                    .findFirst()
                    .ifPresent(tag -> addTo(categoryBalance, tag.getCategory(), t.getAmount()))
        );

        return categoryBalance;
    }

    private void checkOrAdd(Map<C, AM> map, C category, AM amount) {
        if(!map.containsKey(category)) {
            map.put(category, amount);
        } else {
            map.put(category, map.get(category).add(amount));
        }
    }

    private void addTo(List<Map<C, AM>> categoryBalance, C category, AM amount) {
        if(amount.signum() > 0) {
            checkOrAdd(categoryBalance.getFirst(), category, amount);
        } else {
            checkOrAdd(categoryBalance.get(1), category, amount);
        }
    }
}
