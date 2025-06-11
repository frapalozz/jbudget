package it.unicam.cs.mpgc.jbudget125914.model.transaction.details;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public final class FinancialDetail extends AbstractDetail {

    @Column(precision = 14, scale = 2)
    private BigDecimal amount;

    private Currency currency;

    public FinancialDetail(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
