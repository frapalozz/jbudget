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

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * AbstractAccount is an abstract class for all the accounts
 * It as an ID, name and InitialAmount
 * @param <AM> amount type
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAccount<AM> implements Account<AM>, Serializable, Comparable<FinancialAccount> {

    /**
     * Account ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    /**
     * Account Name
     */
    @Column(nullable = false, unique = true, length = 50)
    @Setter
    private String name;

    /**
     * Account Initial Amount
     */
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "initialAmount"))
    private AM initialAmount;

    @Override
    public String toString() {
        return this.getName();
    }
}
