package it.unicam.cs.mpgc.jbudget125914.models.entities.tag;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractTag<C> implements Tag<C> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(length = 50)
    @Setter
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
