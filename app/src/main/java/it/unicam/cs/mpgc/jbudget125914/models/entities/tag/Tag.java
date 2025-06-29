package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import it.unicam.cs.mpgc.jbudget125914.models.entities.Nameable;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import lombok.NonNull;

import java.io.Serializable;

public interface Tag<
        I extends Serializable,
        N,
        C extends Category<I,N,? extends Tag<I,N,C>>> extends Nameable<N> {

    I getTagId();

    C getCategory();

    void setCategory(@NonNull C category);
}
