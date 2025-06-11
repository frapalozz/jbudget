package it.unicam.cs.mpgc.jbudget125914.model.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTransaction<T extends AbstractDetail, D extends Transaction<T, D, V>, V extends Tag<D, V>> implements Transaction<T, D, V>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Embedded
    private T details;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String description;


    public AbstractTransaction(Long transactionId, T details, LocalDate date, String description) {
        this.transactionId = transactionId;
        this.details = details;
        this.date = date;
        this.description = description;
    }
}
