package it.unicam.cs.mpgc.jbudget125914.currency;

import org.jetbrains.annotations.NotNull;

public record Money(Double amount) implements Currency<Money> {

    public Money{
        if(amount == null)
            throw new NullPointerException("Amount cannot be null");
    }

    @Override
    public Double amount() {
        return this.amount;
    }

    @Override
    public boolean isNegative() {
        return this.amount < 0;
    }

    @Override
    public boolean isZero() {
        return this.amount == 0;
    }

    @Override
    public Money sum(Money that) {

        return new Money(this.amount + that.amount);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Money that)) return false;

        return this.amount.doubleValue() == that.amount.doubleValue();
    }

    @Override
    public int compareTo(@NotNull Currency<Money> o) {
        return this.amount.compareTo(o.amount().doubleValue());
    }
}
