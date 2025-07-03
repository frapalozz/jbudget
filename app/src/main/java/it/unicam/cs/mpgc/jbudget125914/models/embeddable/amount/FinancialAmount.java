package it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

/**
 * This class represent a FinancialAmount
 */
@Getter
@Setter
@Embeddable
public class FinancialAmount implements Amount<BigDecimal, FinancialAmount>, Comparable<FinancialAmount> {

    @Column(precision = 14, scale = 2, nullable = false)
    private final BigDecimal amount;

    /**
     * Construct a new Amount
     * @param amount the new amount
     * @throws NullPointerException if {@code amount} is null
     */
    public FinancialAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FinancialAmount() {
        amount = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    @Override
    public int signum() {
        return this.getAmount().signum();
    }

    @Override
    public boolean isZero() {
        return this.signum() == 0;
    }

    @Override
    public FinancialAmount add(FinancialAmount n) {
        if(n == null || n.getAmount() == null) {
            return new FinancialAmount(getAmount());
        }
        return new FinancialAmount(amount.add(n.getAmount()));
    }

    @Override
    public int compareTo(FinancialAmount o) {
        return this.getAmount().compareTo(o.getAmount());
    }
}
