package om.hotelreservation;

import com.hotelreservation.Hotel;
import com.hotelreservation.HotelReservationService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelReservationServiceTest {
    private HotelReservationService hotelReservationService;
    private Hotel lakewood;

    @Before
    public void setup() throws Exception {
        hotelReservationService = new HotelReservationService();
        lakewood = new Hotel("Lakewood", 4);
    }

    @Test
    public void givenHotel_whenInvokedAddHotel_shouldBeAbleToAdd() {
        Assertions.assertTrue(this.hotelReservationService.addHotel(lakewood));
    }

}
