package it.unicam.cs.mpgc.jbudget125914.service.filterService.filters;

public interface ScheduleSelector<S> {

    /**
     * Return the schedule
     * @return the schedule
     */
    S getSchedule();

    /**
     * Set the new schedule
     * @param schedule the new schedule
     */
    void setSchedule(S schedule);
}
