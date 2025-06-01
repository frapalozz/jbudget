package it.unicam.cs.mpgc.jbudget125914.model.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal balance;
}
