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

import it.unicam.cs.mpgc.jbudget125914.interfaces.Nameable;
import it.unicam.cs.mpgc.jbudget125914.interfaces.Categorizable;
import it.unicam.cs.mpgc.jbudget125914.interfaces.Taggable;
import lombok.NonNull;

import java.util.Set;

/**
 * This interface represent a Group
 * @param <T> tag type
 * @param <K> category type
 * @param <C> currency type
 * @param <A> account type
 */
public interface Group<
        T, K, C, A
        > extends Taggable<T>, Categorizable<K>, Nameable<String> {

    /**
     * Return the Group ID
     * @return the Group ID
     */
    Long getGroupId();

    /**
     * Return the Group currency
     * @return the Group currency
     */
    C getCurrency();

    /**
     * Set the new Group currency
     * @param currency the new Group currency
     */
    void setCurrency(@NonNull C currency);

    /**
     * Return the set of Account associated with this Group
     * @return the set of Account associated with this Group
     */
    Set<A> getAccounts();

    /**
     * Set the accounts associated to the Group
     * @param accounts the new accounts
     * @throws NullPointerException if {@code accounts} is null
     */
    void setAccounts(@NonNull Set<A> accounts);
}
