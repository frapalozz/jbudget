package it.unicam.cs.mpgc.jbudget125914;

public enum TransactionType {

    // Past Transaction, only manually added
    PAST_EXPENSE, PAST_INCOME,

    // Future Transaction, automatically added
    FUTURE_AUTO_EXPENSE, FUTURE_AUTO_INCOME,

    // Future Transaction, not automatically added. It's like a **remainder**
    FUTURE_MANUAL_EXPENSE, FUTURE_MANUAL_INCOME,
}
