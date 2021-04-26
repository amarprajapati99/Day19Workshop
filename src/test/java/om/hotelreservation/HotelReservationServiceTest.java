package om.hotelreservation;

import com.hotelreservation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class HotelReservationServiceTest {
    private HotelReservationService hotelReservationService;
    private Hotel lakewood;
    private Hotel bridgewood;
    private Hotel ridgewood;

    @Before
    public void setup() throws Exception {
        hotelReservationService = new HotelReservationService();
        HashMap<CustomerType, Rate> customerTypeRateMap = new HashMap<>();
        customerTypeRateMap.put(CustomerType.regular, new Rate(110, 90));
        customerTypeRateMap.put(CustomerType.reward, new Rate(80, 80));
        lakewood = new Hotel("Lakewood", 3, customerTypeRateMap);

        customerTypeRateMap = new HashMap<>();
        customerTypeRateMap.put(CustomerType.regular, new Rate(160, 40));
        customerTypeRateMap.put(CustomerType.reward, new Rate(110, 50));
        bridgewood = new Hotel("Bridgewood", 4, customerTypeRateMap);

        customerTypeRateMap = new HashMap<>();
        customerTypeRateMap.put(CustomerType.regular, new Rate(220, 150));
        customerTypeRateMap.put(CustomerType.reward, new Rate(100, 40));
        ridgewood = new Hotel("Ridgewood", 5, customerTypeRateMap);
        hotelReservationService.addHotel(lakewood);
        hotelReservationService.addHotel(bridgewood);
        hotelReservationService.addHotel(ridgewood);
    }

    @Test
    public void givenHotel_whenInvokedAddHotel_shouldBeAbleToAdd() {
        Assert.assertTrue(this.hotelReservationService.addHotel(lakewood));
    }

    @Test
    public void givenDateRange_whenSearched_shouldReturnCheapestHotelBasedOnWeekdayAndWeekend() {
        List<Result> cheapestHotelResult =
                hotelReservationService.findCheapestHotelsBasedOnDay(CustomerType.regular,
                        "11Sep2020", "12Sep2020");

        Assertions.assertEquals(2,cheapestHotelResult.size());
        Assertions.assertEquals(200,cheapestHotelResult.get(0).getTotalRate());
    }
    @Test
    public void givenDateRange_whenSearched_shouldReturnCheapestHotelBasedOnWeekdayAndWeekendTrue() {
        List<Result> cheapestHotelResult = hotelReservationService.findCheapestHotelsBasedOnDay(CustomerType.regular,
                "11Sep2020", "12Sep2020");

        Assert.assertEquals(2, cheapestHotelResult.size());
        Assert.assertEquals(200, cheapestHotelResult.get(0).getTotalRate());
    }

    @Test
    public void givenWeekdayAndWeekend_whenCostForEach_shouldBeAddedToWeekdayAndWeekend() {
        Assert.assertEquals(220, this.hotelReservationService.cost(ridgewood));

    }
    @Test
    public void givenHotel_whenInvokedAddRating_shouldBeAbleToAdd() {
        Assert.assertEquals(3,lakewood.getrating(3));
    }
}
