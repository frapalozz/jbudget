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

package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialSchedule;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Set;

/**
 * This class represent a FinancialTag entity
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class FinancialTag extends AbstractTag<FinancialCategory> implements Serializable {

    /**
     * Tag category
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "categoryid", name = "category")
    private FinancialCategory category;

    /**
     * tag group
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "groupid", name = "groupid", nullable = false)
    private FinancialGroup groupId;

    /**
     * tags transactions
     */
    @ManyToMany
    @JoinTable(
            name = "transaction_tag_relationship",
            joinColumns = @JoinColumn(name = "tagid", table = "tag"),
            inverseJoinColumns = @JoinColumn(name = "transactionid", table = "transaction")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FinancialTransaction> transactions;

    /**
     * tags transactions
     */
    @ManyToMany
    @JoinTable(
            name = "schedule_tag_relationship",
            joinColumns = @JoinColumn(name = "tagid", table = "tag"),
            inverseJoinColumns = @JoinColumn(name = "transactionid", table = "schedule")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FinancialSchedule> schedules;

    /**
     * Construct a new FinancialTag
     * @param name name of the Tag
     * @param category category associated to the Tag
     * @throws NullPointerException if any params is null
     */
    public FinancialTag(@NonNull String name, @NonNull FinancialCategory category) {
        setName(name);
        setCategory(category);
    }
}
