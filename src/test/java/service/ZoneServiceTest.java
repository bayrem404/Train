package service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ZoneServiceTest {

    private ZoneService zoneService = new ZoneService();

    @Test
    public void getTripZonesShouldReturnOneTrip() {

        List<List<Integer>> result = zoneService.getTripZones("A","D");

        Assert.assertThat(result.size(), Matchers.is(1));
        Assert.assertThat(result.get(0).get(0), Matchers.is(1));
        Assert.assertThat(result.get(0).get(1), Matchers.is(2));
    }

    @Test
    public void getTripZonesShouldReturnTwoTrips() {

        List<List<Integer>> result = zoneService.getTripZones("A","E");

        Assert.assertThat(result.size(), Matchers.is(2));
        Assert.assertThat(result.get(0).get(0), Matchers.is(1));
        Assert.assertThat(result.get(0).get(1), Matchers.is(2));
        Assert.assertThat(result.get(1).get(0), Matchers.is(1));
        Assert.assertThat(result.get(1).get(1), Matchers.is(3));
    }

    @Test
    public void getTripZonesShouldReturnFourTrips() {

        List<List<Integer>> result = zoneService.getTripZones("C","F");

        Assert.assertThat(result.size(), Matchers.is(4));
        Assert.assertThat(result.get(0).get(0), Matchers.is(2));
        Assert.assertThat(result.get(0).get(1), Matchers.is(3));
        Assert.assertThat(result.get(1).get(0), Matchers.is(2));
        Assert.assertThat(result.get(1).get(1), Matchers.is(4));
        Assert.assertThat(result.get(2).get(0), Matchers.is(3));
        Assert.assertThat(result.get(2).get(1), Matchers.is(3));
        Assert.assertThat(result.get(3).get(0), Matchers.is(3));
        Assert.assertThat(result.get(3).get(1), Matchers.is(4));
    }

    @Test
    public void getBestZoneShouldReturnSameStartAndEndZone() {
        Multimap<Integer,List<Integer>> map = ArrayListMultimap.create();
        map.put(200,Arrays.asList(2,3));
        map.put(200,Arrays.asList(2,2));
        List<Integer> result = zoneService.getBestZone(map);
        Assert.assertThat(result, CoreMatchers.is(Arrays.asList(2,2)));
    }

    @Test
    public void getBestZoneShouldReturnFirstZone() {
        Multimap<Integer,List<Integer>> map = ArrayListMultimap.create();
        map.put(200,Arrays.asList(2,4));
        map.put(200,Arrays.asList(2,3));
        List<Integer> result = zoneService.getBestZone(map);
        Assert.assertThat(result, CoreMatchers.is(Arrays.asList(2,4)));
    }
}
