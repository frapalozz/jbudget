package it.unicam.cs.mpgc.jbudget125914.models.entities.group;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractGroup<TA, C, A> implements Group<TA, C, String, A>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    @Setter
    private String name;

    @Setter
    private String currency;

    @Override
    public String toString() {
        return name;
    }
}
