package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import it.unicam.cs.mpgc.jbudget125914.models.entities.Nameable;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.Category;
import lombok.NonNull;

import java.io.Serializable;

public interface Tag<C extends Category<? extends Tag<C>>> extends Nameable<String> {

    Long getTagId();

    C getCategory();

    void setCategory(@NonNull C category);
}
