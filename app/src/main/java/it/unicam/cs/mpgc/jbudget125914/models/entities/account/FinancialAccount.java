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

import java.io.Serializable;

/**
 * This class represent a FinancialAccount entity
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "account")
public class FinancialAccount implements Account<FinancialAmount>, Serializable, Comparable<FinancialAccount> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true, length = 50)
    @Setter
    private String name;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "initialAmount"))
    private FinancialAmount initialAmount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "groupid", name = "groupid")
    @Setter
    private FinancialGroup groupId;

    /**
     * Constructor for a FinancialAccount
     * @param name name of the FinancialAccount
     * @param initialAmount initialAmount of the FinancialAccount
     * @param group group of the FinancialAccount
     * @throws NullPointerException if any of the params is null
     * @throws IllegalArgumentException if {@code initialAmount} is zero
     */
    public FinancialAccount(String name, FinancialAmount initialAmount, FinancialGroup group) {
        setName(name);
        setInitialAmount(initialAmount);
        setGroupId(group);
    }

    @Override
    public void setInitialAmount(@NonNull FinancialAmount initialAmount) {
        if(initialAmount.signum() == 0) {
            throw new IllegalArgumentException("Initial amount cannot be zero");
        }
        this.initialAmount = initialAmount;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(FinancialAccount o) {
        return this.getName().compareTo(o.getName());
    }
}
