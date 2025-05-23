package it.unicam.cs.mpgc.jbudget125914.manager;

import java.time.LocalDate;

public record DateRange(LocalDate startDate, LocalDate endDate) {

    public static DateRange newRange(Range range) {
        LocalDate currentDate = LocalDate.now();

        return switch (range) {
            case WEEK, MONTH, YEAR -> calcRange(currentDate, range);
            default -> new DateRange(LocalDate.now(), LocalDate.now());
        };
    }

    private static DateRange calcRange(LocalDate currentDate, Range range) {
        return new DateRange(
                currentDate.minusDays(daysFromStart(range, currentDate)),
                currentDate.plusDays(daysToEnd(range, currentDate))
        );
    }

    private static int daysFromStart(Range range, LocalDate date) {

        return switch (range) {
            case WEEK -> date.getDayOfWeek().getValue() - 1;
            case MONTH -> date.getDayOfMonth() - 1;
            case YEAR -> date.getDayOfYear() - 1;
            default -> 0;
        };
    }

    private static int daysToEnd(Range range, LocalDate date) {

        return switch (range) {
            case WEEK -> 7 - date.getDayOfWeek().getValue();
            case MONTH -> date.lengthOfMonth() - date.getDayOfMonth();
            case YEAR -> date.lengthOfYear() - date.getDayOfYear();
            default -> 0;
        };
    }
}
