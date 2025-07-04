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

package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This class represent a FinancialTag entity
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class FinancialTag extends AbstractTag<FinancialCategory> implements Serializable {

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private FinancialCategory category;

    /**
     * Construct a new FinancialTag
     * @param name name of the Tag
     * @param category category associated to the Tag
     */
    public FinancialTag(String name, FinancialCategory category) {
        if(name == null)
            throw new NullPointerException("name is null");
        setName(name);
        this.category = category;
    }
}
