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

package it.unicam.cs.mpgc.jbudget125914.service.filterService;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * AbstractFilterManager is an abstract class for all the FilterManagers
 * @param <T> transaction type
 * @param <S> schedule type
 * @param <A> account type
 * @param <D> data type
 * @param <TA> tag type
 * @param <G> group type
 */
@Getter
@Setter
public abstract class AbstractFilterService<T, S, A, D, TA, G> implements FilterService<T,S,A,D,TA,G> {

    private D startDate;

    private D endDate;

    private Set<A> accounts;

    private Set<TA> tags;

    private G group;

    private T transaction;

    private S schedule;
}
