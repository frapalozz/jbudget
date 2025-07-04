package it.unicam.cs.mpgc.jbudget125914.models.entities.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAccount<AM> implements Account<AM>, Serializable, Comparable<FinancialAccount> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true, length = 50)
    @Setter
    private String name;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "initialAmount"))
    private AM initialAmount;

    @Override
    public String toString() {
        return this.getName();
    }
}
