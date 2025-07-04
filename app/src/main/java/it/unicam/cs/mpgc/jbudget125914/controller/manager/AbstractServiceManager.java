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

package it.unicam.cs.mpgc.jbudget125914.controller.manager;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.Account;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.Group;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.fetchManager.FetchManager;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.FilterManager;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.generalManager.GeneralManager;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.temporal.Temporal;

/**
 * AbstractServiceManager is the abstract class for all service manager
 * @param <T> transaction type
 * @param <S> schedule type
 * @param <A> account type
 * @param <TA> tag type
 * @param <N> number type
 * @param <D> data type
 * @param <AM> amount type
 * @param <C> category type
 * @param <G> group type
 * @param <GM> GeneralManager type
 * @param <FM> FilterManager type
 * @param <FTC> FetchManager type
 */
@Getter
@Setter
public abstract class AbstractServiceManager<
        T extends Transaction<AM, D, TA, A>,
        S extends Transaction<AM, D, TA, A>,
        A extends Account<AM>,
        TA extends Tag<C>,
        N extends Number,
        D extends Temporal & Comparable<? super D>,
        AM extends Amount<N ,AM>,
        C extends Category<TA>,
        G extends Group<TA, C, ?, A>,
        GM extends GeneralManager<T,S,AM,N,A,D,TA,C,G,?,?,?,?,?,?>,
        FM extends FilterManager<T,S,AM,N,A,D,TA,C,G>,
        FTC extends FetchManager<T,S,AM,N,A,D,TA,C,G,GM,FM>> implements ServiceManager<
            D, S, C, A, TA, T, AM, N, G,GM,FM,FTC> {

    private SimpleBooleanProperty changes = new SimpleBooleanProperty(false);

    private FTC fetchManager;

    private FM filterManager;

    private GM generalManager;

}
