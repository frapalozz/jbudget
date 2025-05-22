package it.unicam.cs.mpgc.jbudget125914.currency;

public record Money(Double quantity, String unit) implements UnitMeasure<String> {

    @Override
    public Double quantity() {
        return this.quantity;
    }

    @Override
    public String unit() {
        return this.unit;
    }

    @Override
    public boolean isExpense() {
        return this.quantity() < 0.0;
    }

    public String toString() {
        return this.quantity + " " + this.unit;
    }
}
