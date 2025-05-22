package it.unicam.cs.mpgc.jbudget125914;

public record Money(Double quantity, String unit) implements UnitMeasure<Double, String> {
    @Override
    public Double getQuantity() {
        return this.quantity;
    }

    @Override
    public String getUnit() {
        return this.unit;
    }
}
