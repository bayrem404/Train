package service;

import json.model.CustomerSummary;
import json.model.Tap;
import json.model.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerSummaryService {

    public List<CustomerSummary> getCustomerSummaries (Map<Integer, List<Tap>> tapsByCustomer) {
        List<CustomerSummary> customerSummaries = new ArrayList<>();
        ZoneService zoneService = new ZoneService();
        PriceService priceService = new PriceService();

        for (Map.Entry entry : tapsByCustomer.entrySet()) {
            int totalCostInCents = 0;
            List<Trip> tripList = new ArrayList<>();
            for (int i = 0; i<((List<Tap>)entry.getValue()).size(); i+=2) {
                Trip trip = new Trip();
                trip.setStationStart(((List<Tap>)entry.getValue()).get(i).getStation());
                trip.setStationEnd(((List<Tap>)entry.getValue()).get(i+1).getStation());
                trip.setStartedJourneyAt(((List<Tap>)entry.getValue()).get(i).getUnixTimestamp());
                List<List<Integer>> tripZones = zoneService.getTripZones(trip.getStationStart(), trip.getStationEnd());
                trip.setCostInCents(priceService.getBestPriceAndZone(tripZones).keySet().iterator().next());
                trip.setZoneFrom(priceService.getBestPriceAndZone(tripZones).entrySet().iterator().next().getValue().get(0));
                trip.setZoneTo(priceService.getBestPriceAndZone(tripZones).entrySet().iterator().next().getValue().get(1));
                tripList.add(trip);
                totalCostInCents = totalCostInCents + trip.getCostInCents();
            }
            customerSummaries.add(new CustomerSummary((int)entry.getKey(),totalCostInCents, tripList));
        }
        return customerSummaries;
    }
}
