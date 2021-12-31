package service;

import json.model.CustomerSummary;
import json.model.Tap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    }

}
