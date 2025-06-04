package it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Getter
@Embeddable
@Immutable
public final class FinancialDetail extends AbstractDetail {

    @Column(precision = 14, scale = 2)
    private final BigDecimal amount;

    private final Currency currency;

    public FinancialDetail(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public FinancialDetail() {
        this.amount = BigDecimal.ZERO;
        this.currency = Currency.getInstance(Locale.getDefault());
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
