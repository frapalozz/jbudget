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

package it.unicam.cs.mpgc.jbudget125914.models.entities.account;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

/**
 * This class represent a FinancialAccount entity
 */
@Setter
@Entity
@Getter
@NoArgsConstructor
@Table(name = "account")
public class FinancialAccount extends AbstractAccount<FinancialAmount> implements Serializable, Comparable<FinancialAccount> {

    /**
     * Account group
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "groupid", name = "groupid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FinancialGroup groupId;

    /**
     * Constructor for a FinancialAccount
     * @param name name of the FinancialAccount
     * @param initialAmount initialAmount of the FinancialAccount
     * @param group group of the FinancialAccount
     * @throws NullPointerException if any of the params is null
     * @throws IllegalArgumentException if {@code initialAmount} is zero
     */
    public FinancialAccount(@NonNull String name, @NonNull FinancialAmount initialAmount, @NonNull FinancialGroup group) {
        setName(name);
        setInitialAmount(initialAmount);
        setGroupId(group);
    }

    @Override
    public int compareTo(FinancialAccount o) {
        return this.getName().compareTo(o.getName());
    }
}
