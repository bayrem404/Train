package service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class PriceService {

    private ZoneService zoneService = new ZoneService();

    private int getPrice(List<Integer> zones) {
        if ((zones.get(0).equals(zones.get(1)) && (zones.contains(1) || zones.contains(2))) || (zones.contains(1) && zones.contains(2))) {
            return 240;
        }
        if ((zones.get(0).equals(zones.get(1)) && (zones.contains(3) || zones.contains(4))) || (zones.contains(3) && zones.contains(4))) {
            return 200;
        }
        if (zones.contains(1) && zones.contains(3) || zones.contains(2) && zones.contains(3) ) {
            return 280;
        }
        if (zones.contains(1) && zones.contains(4) || zones.contains(2) && zones.contains(4) ) {
            return 300;
        }
        return 0;
    }

    public Map<Integer,List<Integer>> getBestPriceAndZone(List<List<Integer>> zones) {
        Multimap<Integer,List<Integer>> map = ArrayListMultimap.create();
        for (int i=0; i<zones.size(); i++) {
            int computedPrice = getPrice(zones.get(i));
            map.put(computedPrice,zones.get(i));
        }
        Map<Integer,List<Integer>> bestPriceAndZone = new HashMap<>();
        Integer minPrice = Collections.min(map.keySet());
        List<Integer> bestZone = zoneService.getBestZone(map);
        bestPriceAndZone.put(minPrice,bestZone);
        return bestPriceAndZone;
    }
}
