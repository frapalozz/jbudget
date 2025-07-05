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

package it.unicam.cs.mpgc.jbudget125914.models.entities.category;

import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * This class represent a FinancialCategory entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class FinancialCategory extends AbstractCategory<FinancialTag> implements Serializable {

    /**
     * Category Tags
     */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<FinancialTag> tags;

    /**
     * Construct a new FinancialCategory
     * @param name name of the Category
     * @param tags tags associated to the Category
     */
    public FinancialCategory(String name, Set<FinancialTag> tags) {
        setName(name);
        setTags(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinancialCategory that)) return false;
        return getCategoryId().equals(that.getCategoryId()) && getName().equals(that.getName());
    }
}
