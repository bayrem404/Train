package service;

import com.spotify.hamcrest.pojo.IsPojo;
import json.model.CustomerSummary;
import json.model.Tap;
import json.model.Trip;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerSummaryServiceTest {

    @Mock
    private PriceService priceService;
    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private CustomerSummaryService customerSummaryService = new CustomerSummaryService();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCustomerSummeriesShouldReturn() {
        Tap tap1 = new Tap(1,1,"A");
        Tap tap2 = new Tap(2,1,"B");
        Tap tap3 = new Tap(3,2,"C");
        Tap tap4 = new Tap(4,2,"D");
        Tap tap5 = new Tap(5,2,"E");
        Tap tap6 = new Tap(6,2,"F");

        Map<Integer, List<Tap>> tapsByCustomer = new HashMap<>();
        tapsByCustomer.put(1, Arrays.asList(tap1,tap2));
        tapsByCustomer.put(2,Arrays.asList(tap3, tap4, tap5, tap6));

        List<CustomerSummary> result = customerSummaryService.getCustomerSummaries(tapsByCustomer);

        Assert.assertThat(result.get(0), IsPojo.pojo(CustomerSummary.class)
                .withProperty("customerId", Matchers.is(1))
                .withProperty("totalCostInCents",Matchers.is(240)));
        Assert.assertThat(result.get(0).getTrips().size(), Matchers.is(1));
        Assert.assertThat(result.get(0).getTrips().get(0), IsPojo.pojo(Trip.class)
                .where(Trip::getStationStart, Matchers.is("A"))
                .where(Trip::getStationEnd, Matchers.is("B"))
                .where(Trip::getStartedJourneyAt, Matchers.is(1L))
                .where(Trip::getCostInCents, Matchers.is(240))
                .where(Trip::getZoneFrom, Matchers.is(1))
                .where(Trip::getZoneTo, Matchers.is(1)));

        Assert.assertThat(result.get(1), IsPojo.pojo(CustomerSummary.class)
                .withProperty("customerId", Matchers.is(2))
                .withProperty("totalCostInCents",Matchers.is(440)));
        Assert.assertThat(result.get(1).getTrips().size(), Matchers.is(2));
        Assert.assertThat(result.get(1).getTrips().get(0), IsPojo.pojo(Trip.class)
                .where(Trip::getStationStart, Matchers.is("C"))
                .where(Trip::getStationEnd, Matchers.is("D"))
                .where(Trip::getStartedJourneyAt, Matchers.is(3L))
                .where(Trip::getCostInCents, Matchers.is(240))
                .where(Trip::getZoneFrom, Matchers.is(2))
                .where(Trip::getZoneTo, Matchers.is(2)));
        Assert.assertThat(result.get(1).getTrips().get(1), IsPojo.pojo(Trip.class)
                .where(Trip::getStationStart, Matchers.is("E"))
                .where(Trip::getStationEnd, Matchers.is("F"))
                .where(Trip::getStartedJourneyAt, Matchers.is(5L))
                .where(Trip::getCostInCents, Matchers.is(200))
                .where(Trip::getZoneFrom, Matchers.is(3))
                .where(Trip::getZoneTo, Matchers.is(3)));
    }

}
