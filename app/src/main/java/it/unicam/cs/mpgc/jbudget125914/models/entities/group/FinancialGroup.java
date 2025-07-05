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

package it.unicam.cs.mpgc.jbudget125914.models.entities.group;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represent a FinancialGroup entity
 */
@Setter
@Entity
@NoArgsConstructor
@Getter
@Table(name = "group_table")
public class FinancialGroup extends AbstractGroup<FinancialTag, FinancialCategory, FinancialAccount> implements Serializable {

    /**
     * Group tags
     */
    @OneToMany(mappedBy = "groupId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FinancialTag> tags;

    /**
     * Group accounts
     */
    @OneToMany(mappedBy = "groupId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FinancialAccount> accounts;

    /**
     * Group categories
     */
    @OneToMany(mappedBy = "groupId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FinancialCategory> categories;

    /**
     * Financial Group constructor
     * @param name name of the group
     * @param currency currency of the group
     * @throws NullPointerException if any of the params is null
     */
    public FinancialGroup(@NonNull String name, @NonNull String currency) {
        this(name, currency, new HashSet<>());
    }

    /**
     * Construct a new FinancialGroup
     * @param name name of the Group
     * @param currency currency of the Group
     * @param accounts accounts associated to the Group
     * @throws NullPointerException if any of the params are null
     */
    FinancialGroup(@NonNull String name, @NonNull String currency, @NonNull Set<FinancialAccount> accounts) {
        setName(name);
        setCurrency(currency);
        setAccounts(accounts);
    }
}
