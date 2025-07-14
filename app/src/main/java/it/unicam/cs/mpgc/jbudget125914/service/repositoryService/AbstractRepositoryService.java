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

import lombok.Getter;

/**
 * AbstractRepositoryManager is an abstract class for all the repository manager
 * @param <T> transaction type
 * @param <D> data type
 * @param <TR> transaction Repository type
 * @param <AR> account Repository type
 * @param <CR> category Repository type
 * @param <GR> group Repository type
 * @param <TAR> tag Repository type
 * @param <SR> schedule Repository type
 */
@Getter
public abstract class AbstractRepositoryService<
        T, D, TR, AR, CR, GR, TAR, SR
        > implements RepositoryService<T,D,TR,AR,CR,GR,TAR,SR> {

    private final TR transactionRepository;

    private final AR accountRepository;

    private final CR categoryRepository;

    private final GR groupRepository;

    private final TAR tagRepository;

    private final SR scheduleRepository;

    public AbstractRepositoryService(TR tr, AR ar, CR cr, GR gr, TAR tar, SR sr) {
        this.transactionRepository = tr;
        this.accountRepository = ar;
        this.categoryRepository = cr;
        this.groupRepository = gr;
        this.tagRepository = tar;
        this.scheduleRepository = sr;
    }
}
