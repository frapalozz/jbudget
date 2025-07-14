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

package it.unicam.cs.mpgc.jbudget125914.service;

import it.unicam.cs.mpgc.jbudget125914.service.fetchService.FinancialFetchService;
import it.unicam.cs.mpgc.jbudget125914.service.filterService.FinancialFilterService;
import it.unicam.cs.mpgc.jbudget125914.service.repositoryService.FinancialRepositoryService;

/**
 * This class represent a Financial service manager.
 * It is as singleton, used to maintain a single controller for all the UI
 */
public final class FinancialService extends AbstractService<
        FinancialRepositoryService,
        FinancialFilterService,
        FinancialFetchService> {

    private FinancialService() {
        super(new FinancialFetchService(), new FinancialFilterService(), new FinancialRepositoryService());
    }

    private static FinancialService instance = new FinancialService();

    /**
     * Return the instance of this FinancialServiceManager
     * @return the instance of this FinancialServiceManager
     */
    public static FinancialService getInstance() {
        if(instance == null) {
            instance = new FinancialService();
        }
        return instance;
    }

    @Override
    public void update() {
        getFetchManager().update(getRepositoryManager(), getFilterManager(), this::render);
    }

    private void render() {
        getChanges().set(!getChanges().getValue());
    }
}
