package service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PriceServiceTest {

    @Mock
    private ZoneService zoneService = new ZoneService();

    @InjectMocks
    private PriceService priceService = new PriceService();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBestPriceShouldReturnMinPriceAndStaInSameZone() {

        List<List<Integer>> zones = new ArrayList<>();
        zones.add(Arrays.asList(2,3));
        zones.add(Arrays.asList(2,4));
        zones.add(Arrays.asList(3,3));
        zones.add(Arrays.asList(3,4));

        Mockito.when(zoneService.getBestZone(Mockito.anyObject())).thenReturn(Arrays.asList(3,3));

        Map<Integer, List<Integer>> result = priceService.getBestPriceAndZone(zones);

        Assert.assertThat(result.entrySet().iterator().next().getKey(), CoreMatchers.is(200));
        Assert.assertThat(result.entrySet().iterator().next().getValue(), CoreMatchers.is(Arrays.asList(3,3)));

    }

}
