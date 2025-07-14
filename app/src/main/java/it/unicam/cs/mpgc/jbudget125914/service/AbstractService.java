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

import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;

/**
 * AbstractServiceManager is the abstract class for all service manager
 * @param <GM> GeneralManager type
 * @param <FM> FilterManager type
 * @param <FTC> FetchManager type
 */
@Getter
public abstract class AbstractService<GM, FM, FTC> implements Service<GM,FM,FTC> {

    private final SimpleBooleanProperty changes = new SimpleBooleanProperty(false);

    private final FTC fetchManager;

    private final FM filterManager;

    private final GM repositoryManager;

    public AbstractService(FTC ftm, FM fm, GM rm) {
        this.fetchManager = ftm;
        this.filterManager = fm;
        this.repositoryManager = rm;
    }
}
