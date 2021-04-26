package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/* @Description - Add hotel in hotel reservation system */

public class HotelReservationService<weekDay, Weekend>{
    private static final DateTimeFormatter DATE_RANGE_FORMAT = DateTimeFormatter.ofPattern("ddMMMyyyy");
    private List<Hotel> hotels;

    public HotelReservationService() {
        this.hotels = new ArrayList<>();
    }
    public boolean addHotel(Hotel hotel) {
        return this.hotels.add(hotel);
    }
    /* @Description - To find the cheapest hotel to given range
     * to find cheapest hotel based on rated hotel.*/

    public List<Result> findCheapestHotelsBasedOnDay(CustomerType customerType, String initialDateRange,
                                                     String endDateRange) {
        LocalDate initialDate = LocalDate.parse(initialDateRange, DATE_RANGE_FORMAT);
        LocalDate endDate = LocalDate.parse(endDateRange, DATE_RANGE_FORMAT);

        List<Result> results = this.hotels.stream().map(hotel -> {
            Result result = new Result();
            result.setHotelName(hotel.name);
            result.setTotalRate(hotel.getTotalRate(customerType, initialDate, endDate));
            return result;
        }).sorted(Comparator.comparing(Result::getTotalRate)).collect(Collectors.toList());

        return results.stream().filter(result -> result.getTotalRate() == results.get(0).getTotalRate())
                .collect(Collectors.toList());
    }
    /* @Description - Add week day .*/

    public String addweekday(int day) {
        String dayName = "";
        switch (day) {
            case 1: dayName = "Monday"; break;
            case 2: dayName = "Tuesday"; break;
            case 3: dayName = "Wednesday"; break;
            case 4: dayName = "Thursday"; break;
            case 5: dayName = "Friday"; break;
            default:dayName = "Invalid day range";
        }

        return dayName;
    }
    /* @Description - Add weekend day rates for each hotel .*/

    public String addweekend(int day) {
        String dayName = "";
        switch (day) {
            case 1: dayName = "Saturday"; break;
            case 2: dayName = "Sunday"; break;
            default:dayName = "Invalid day range";
        }
        return dayName;
    }

    public int cost(Hotel hotel) {
        LocalDate todayDate = LocalDate.now();
        if (todayDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || todayDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            return hotel.rate.get(CustomerType.regular).getWeekendRates();
        else
            return hotel.rate.get(CustomerType.regular).getWeekdayRates();
    }

    public int cost1(Hotel bridgewood) {
        LocalDate todayDate = LocalDate.now();
        if (todayDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || todayDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            return bridgewood.rate.get(CustomerType.regular).getWeekendRates();
        else
            return bridgewood.rate.get(CustomerType.regular).getWeekdayRates();
    }

    public int cost2(Hotel ridgewood) {
        LocalDate todayDate = LocalDate.now();
        if (todayDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || todayDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            return ridgewood.rate.get(CustomerType.regular).getWeekendRates();
        else
            return ridgewood.rate.get(CustomerType.regular).getWeekdayRates();
    }
    /* to find cheapest best rated hotel, hotel for given date range.*/

    public List<Result> findCheapestBestRatedHotelforGivenDateRange(CustomerType customerType, String initialDateRange,
                                                                    String endDateRange) {
        LocalDate initialDate = LocalDate.parse(initialDateRange, DATE_RANGE_FORMAT);
        LocalDate endDate = LocalDate.parse(endDateRange, DATE_RANGE_FORMAT);

        List<Result> results = this.hotels.stream().map(hotel -> {
            Result result = new Result();
            result.setHotelName(hotel.name);
            result.setRating(hotel.getRating());
            result.setTotalRate(hotel.getTotalRate(customerType, initialDate, endDate));
            return result;
        }).sorted(Comparator.comparing(Result::getTotalRate)
                .thenComparing(Comparator.comparing(Result::getRating).reversed())).collect(Collectors.toList());
        return results;

    }
    /* to find  best rated hotel, hotel for given date range.*/
    public List<Result> findBestRatedHotelforGivenDateRange(CustomerType customerType, String initialDateRange,
                                                             String endDateRange) {
        LocalDate initialDate = LocalDate.parse(initialDateRange, DATE_RANGE_FORMAT);
        LocalDate endDate = LocalDate.parse(endDateRange, DATE_RANGE_FORMAT);
        List<Result> results = this.hotels.stream().map(hotel -> {
            Result result = new Result();
            result.setHotelName(hotel.name);
            result.setRating(hotel.getRating());
            result.setTotalRate(hotel.getTotalRate(customerType, initialDate, endDate));
            return result;
        }).sorted(Comparator.comparing(Result::getRating).reversed())
                .collect(Collectors.toList());
        return results;
    }
    /* @Description - Add special rates for reward customer as a part of loyalty program.*/
    public int costReward(Hotel hotel) {
        LocalDate todayDate = LocalDate.now();
        if (todayDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || todayDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            return Hotel.rate.get(CustomerType.reward).getWeekendRates();
        else
            return Hotel.rate.get(CustomerType.reward).getWeekdayRates();
    }
}
