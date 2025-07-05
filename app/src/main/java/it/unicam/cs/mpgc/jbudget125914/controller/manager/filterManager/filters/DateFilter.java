package it.unicam.cs.mpgc.jbudget125914.controller.manager.filterManager.filters;

public interface DateFilter<D> {

    /**
     * Return the start date
     * @return the start date
     */
    D getStartDate();

    /**
     * Set the new start date
     * @param startDate the new start date
     */
    void setStartDate(D startDate);

    /**
     * Return the end date
     * @return the end date
     */
    D getEndDate();

    /**
     * Set the new end date
     * @param endDate the new end date
     */
    void setEndDate(D endDate);
}
