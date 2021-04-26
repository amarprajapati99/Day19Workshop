package com.hotelreservation;

import java.util.ArrayList;
import java.util.List;
/* @Description - Add hotel in hotel reservation system */

public class HotelReservationService {
    private List<Hotel> hotels;

    public HotelReservationService() {
        this.hotels = new ArrayList<>();
    }
    public boolean addHotel(Hotel hotel) {
        return this.hotels.add(hotel);
    }
}
