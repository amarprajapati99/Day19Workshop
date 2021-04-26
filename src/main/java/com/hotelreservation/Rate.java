package com.hotelreservation;

public class Rate {
    private Integer weekendRates;
    private Integer weekdayRates;

    public Rate(Integer weekEndRates, Integer weekDayRates) {
        this.weekendRates = weekEndRates;
        this.weekdayRates = weekDayRates;
    }

    public int getWeekendRates() {
        return weekendRates;
    }

    public int getWeekdayRates() {
        return weekdayRates;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "weekendRates=" + weekendRates +
                ", weekdayRates=" + weekdayRates +
                '}';
    }
}
