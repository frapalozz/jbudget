package it.unicam.cs.mpgc.jbudget125914.models.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
public class FinancialSchedule extends AbstractTransaction<FinancialAmount, LocalDate, FinancialTag, FinancialAccount> {

    @ManyToOne
    @JoinColumn(referencedColumnName = "accountid", name = "account")
    private FinancialAccount account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "schedule_tag_relationship",
            joinColumns = @JoinColumn(name = "transactionid", table = "schedule"),
            inverseJoinColumns = @JoinColumn(name = "tagid", table = "tag")
    )
    private Set<FinancialTag> tags;

    /**
     * Construct a new FinancialTransaction
     * @param amount amount of the Transaction
     * @param date date of the Transaction
     * @param description description of the Transaction
     * @param account account associated to the Transaction
     * @param tags tags associated to the Transaction
     * @throws NullPointerException if any of the params are null
     * @throws IllegalArgumentException if {@code amount} is ZERO
     */
    public FinancialSchedule(
            FinancialAmount amount,
            LocalDate date,
            String description,
            FinancialAccount account,
            Set<FinancialTag> tags) {

        setAccount(account);
        setTags(tags);
        setAmount(amount);
        setDate(date);
        setDescription(description);
    }
}
