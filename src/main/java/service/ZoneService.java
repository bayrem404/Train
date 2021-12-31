package service;

import com.google.common.collect.Multimap;

import java.util.*;

public class ZoneService {

    private List<Integer> getZone(String station) {
        switch (station) {
            case "A":
            case "B":
                return Collections.singletonList(1);

            case "C":
            case "E":
                return Arrays.asList(2,3);

            case "D":
                return Collections.singletonList(2);

            case "F":
                return Arrays.asList(3,4);
            case "G":
            case "H":
            case "I":
                return Collections.singletonList(4);
        }
        return null;
    }

    public List<List<Integer>>  getTripZones(String stationStart, String stationEnd) {
        List<List<Integer>> tripZone = new ArrayList<>();
        for (int i=0; i<getZone(stationStart).size(); i++) {
            for (int j=0; j<getZone(stationEnd).size(); j++) {
                tripZone.add(Arrays.asList(getZone(stationStart).get(i), getZone(stationEnd).get(j)));
            }
        }
        return tripZone;
    }

    public List<Integer> getBestZone(Multimap<Integer,List<Integer>> map) {
        for (List<Integer> zone : map.values()) {
            if(zone.stream().distinct().count() <= 1) {
                return zone;
            } else {
                continue;
            }
        }
        return map.values().iterator().next();
    }
}
