package it.unicam.cs.mpgc.jbudget125914.models.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.Amount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
public abstract class AbstractTransaction<AM extends Amount<? extends Number,AM>, D, TA, A> implements Transaction<AM, D, TA, A> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(columnDefinition = "DATE")
    private D date;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private AM amount;

    @Override
    public void setAmount(@NonNull AM amount) {
        if(amount.isZero())
            throw new IllegalArgumentException("Amount must be greater or less than zero");
        this.amount = amount;
    }
}
