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

package it.unicam.cs.mpgc.jbudget125914.currency;

/**
 * A currency with his amount and currency type
 * @param <C> the type of the amount of this currency
 */
public interface Currency<C extends Currency<C>> extends Comparable<Currency<C>> {

    /**
     * Return the amount of this currency
     * @return the amount of this currency
     */
    Number amount();

    /**
     * Return this currency
     * @return this currency
     */
    String currency();

    /**
     * Return true if this currency amount is negative, otherwise false
     * @return true if this currency amount is negative, otherwise false
     */
    boolean isNegative();

    /**
     * Return true if this currency has an amount of 0, otherwise false
     * @return true if this currency has an amount of 0, otherwise false
     */
    boolean isZero();

    /**
     * Return the sum of this amount + currency amount
     * @param currency the new currency to sum with
     * @return the sum of this amount + currency amount
     */
    C sum(C currency);

    /**
     * Return the subtraction of this amount - currency amount
     * @param currency the currency to subtract with
     * @return the sum of this amount - currency amount
     */
    C subtract(C currency);
}
