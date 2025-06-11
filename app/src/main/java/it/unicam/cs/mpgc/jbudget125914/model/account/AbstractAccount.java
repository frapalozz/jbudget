package it.unicam.cs.mpgc.jbudget125914.model.account;

import it.unicam.cs.mpgc.jbudget125914.model.tag.Tag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractAccount<T extends AbstractDetail, D extends Transaction<T, D, V>, V extends Tag<D, V>> implements Account<T, D, V> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Embedded
    private T initialDetails;

    public AbstractAccount(Long id, String name, String description, T initialDetails) {
        this.accountId = id;
        this.name = name;
        this.description = description;
        this.initialDetails = initialDetails;
    }
}
