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

import it.unicam.cs.mpgc.jbudget125914.models.entities.Nameable;

import lombok.NonNull;

/**
 * This interface represent an Account
 * @param <A> amount type
 */
public interface Account<A> extends Nameable<String> {

    /**
     * Return the Account initial Amount
     * @return the Account initial Amount
     */
    A getInitialAmount();

    /**
     * Set the new Account initialAmount
     * @param initialAmount the new initialAmount
     * @throws IllegalArgumentException if {@code initialAmount} is zero
     * @throws NullPointerException if {@code initialAmount} is null
     */
    void setInitialAmount(@NonNull A initialAmount);
}
