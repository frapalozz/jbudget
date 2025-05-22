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

package it.unicam.cs.mpgc.jbudget125914.transaction;


import it.unicam.cs.mpgc.jbudget125914.categories.Category;
import it.unicam.cs.mpgc.jbudget125914.currency.UnitMeasure;

import java.util.Date;
import java.util.Set;

/**
 * The Transaction interface gives the user a simple way to access to the name, value, date and Categories
 * of a Transaction
 * @author Francesco Palozzi
 */
public interface Transaction<T extends UnitMeasure<?>, C extends Category<?>> {

    /**
     * Returns the name of this Transaction
     * @return the name of this Transaction
     */
    String name();

    /**
     * Returns the value of this Transaction
     * @return the value of this Transaction
     */
    T value();

    /**
     * Returns the date when this Transaction occurred
     * @return the date when this Transaction occurred
     */
    Date date();

    /**
     * Returns the Category set of this Transaction
     * @return the Category set of this Transaction
     */
    Set<C> categories();
}
