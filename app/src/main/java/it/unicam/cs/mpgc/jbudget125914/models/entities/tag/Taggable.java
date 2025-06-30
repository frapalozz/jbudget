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

import lombok.NonNull;

import java.util.Set;

/**
 * This interface gives you the ability to tag an object
 * @param <T> tag type
 */
public interface Taggable<T> {

    /**
     * Return the set of Tag associated
     * @return the set of Tag associated
     */
    Set<T> getTags();

    /**
     * Add a new Tag
     * @param tag the new Tag to add
     * @throws NullPointerException if {@code tag} is null
     */
    default void addTag(@NonNull T tag) {
        getTags().add(tag);
    }

    /**
     * Remove a Tag
     * @param tag the Tag to remove
     * @throws NullPointerException if {@code tag} is null
     */
    default void removeTag(@NonNull T tag) {
        getTags().remove(tag);
    }

    /**
     * Set the new tags
     * @param tags the new tags
     * @throws NullPointerException if {@code tags} is null
     */
    default void setTags(@NonNull Set<T> tags) {
        getTags().clear();
        getTags().addAll(tags);
    }
}
