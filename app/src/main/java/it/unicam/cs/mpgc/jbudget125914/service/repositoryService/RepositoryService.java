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

package it.unicam.cs.mpgc.jbudget125914.service.repositoryService;

/**
 * GeneralManager is an interface that gives the ability to access the Repository
 * @param <T> transaction type
 * @param <D> data type
 * @param <TR> transaction Repository type
 * @param <AR> account Repository type
 * @param <CR> category Repository type
 * @param <GR> group Repository type
 * @param <TAR> tag Repository type
 * @param <SR> schedule Repository type
 */
public interface RepositoryService<T, D, TR, AR, CR, GR, TAR, SR> extends RecurrentTransactions<D,T> {

    /**
     * Return the transaction Repository
     * @return the transaction Repository
     */
    TR getTransactionRepository();

    /**
     * Return the account Repository
     * @return the account Repository
     */
    AR getAccountRepository();

    /**
     * Return the category Repository
     * @return the category Repository
     */
    CR getCategoryRepository();

    /**
     * Return the group Repository
     * @return the group Repository
     */
    GR getGroupRepository();

    /**
     * Return the tag Repository
     * @return the tag Repository
     */
    TAR getTagRepository();

    /**
     * Return the schedule Repository
     * @return the schedule Repository
     */
    SR getScheduleRepository();
}
