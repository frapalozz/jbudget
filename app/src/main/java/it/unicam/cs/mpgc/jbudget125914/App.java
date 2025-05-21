package it.unicam.cs.mpgc.jbudget125914;


import org.joda.money.Money;

public class App {

    static Money money = Money.parse("EUR 36.76");
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(money.getAmount() + money.getCurrencyUnit().toString());
    }
}
