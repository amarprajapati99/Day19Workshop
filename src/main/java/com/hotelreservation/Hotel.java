package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

public class Hotel {
    public String name;
    public int rating;
    public Map<CustomerType, Rate> rate;

    public Hotel(String name, int rating, Map<CustomerType, Rate> rate) {
        this.name = name;
        this.rating = rating;
        this.rate = rate;
    }

    public int getTotalRate(CustomerType customerType, LocalDate initialDate, LocalDate endDate) {
        return Stream.iterate(initialDate, date -> date.plusDays(1))
                .limit(endDate.getDayOfMonth() - initialDate.getDayOfMonth() + 1)
                .map(date -> {
                    if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                        return this.rate.get(customerType).getWeekendRates();
                    return this.rate.get(customerType).getWeekdayRates();
                }).reduce((total, next) -> total + next).get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getrating(int i) {
        return this.rating;
    }
}
