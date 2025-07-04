package it.unicam.cs.mpgc.jbudget125914.models.entities.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCategory<TA> implements Category<TA>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(length = 50)
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
